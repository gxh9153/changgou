package com.changgou.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.api.CommonResult;
import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.pojo.Sku;
import com.changgou.search.dao.SkuEsMapper;
import com.changgou.search.pojo.SkuInfo;
import com.changgou.search.service.SkuEsService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;

import java.util.*;

/**
 * @Author:gxh
 * @Date: 2021/1/4 16:55
 */
@Service
public class SkuEsServiceImpl implements SkuEsService {

    @Autowired
    private SkuEsMapper skuEsMapper;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public Map<String, Object> search(Map<String, String> searchMap) {
        /**
         * 搜索条件封装
         */
        NativeSearchQueryBuilder builder = searchBasicQuery(searchMap);

        /**
         * 集合搜索
         */
        Map<String, Object> resultMap = searchList(builder);

        /**
         * 分类分组查询
         * 如果搜索条件为空则进行分组搜索
         */
        if(searchMap == null || StringUtils.isEmpty(searchMap.get("category"))){
            List<String> categoryList = searchFieldList(builder,"skuCategory","categoryName");
            resultMap.put("categoryList",categoryList);
        }

        /**
         * 品牌分组查询
         */
        if(searchMap == null || StringUtils.isEmpty(searchMap.get("brand"))){
            List<String> brandList = searchFieldList(builder,"skuBrand","brandName");
            resultMap.put("brandList",brandList);
        }


        /**
         * 规格分组查询
         */
        List<String> specList = searchFieldList(builder, "skuSpec", "spec.keyword");

        //定义返回的数据格式
        Map<String, Set<String>> allSpec = new HashMap<String,Set<String>>();
        dataMerge(specList, allSpec);
        resultMap.put("allSpec",allSpec);
        return resultMap;
    }

    private void dataMerge(List<String> specList, Map<String, Set<String>> allSpec) {
        //循环specList
        for (String spec : specList) {
            //将json格式的数据转换成map类型
            Map<String,String> specMap = JSON.parseObject(spec, Map.class);
            //循环specMap
            for (Map.Entry<String, String> entry : specMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Set<String> specValues = allSpec.get(key);
                if(specValues == null){
                    specValues = new HashSet<String>();
                }
                specValues.add(value);
                allSpec.put(key,specValues);
            }
        }
    }

    /**
     * 搜索条件封装
     * @param searchMap
     * @return
     */
    private NativeSearchQueryBuilder searchBasicQuery(Map<String, String> searchMap) {
        //搜索条件构建对象，用户封装各种搜索条件
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();

        //BoolQueryBuilder  根据多条件组合查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();


        if(searchMap != null && searchMap.size() >0){
            //根据关键词搜索
            String keywords = searchMap.get("keywords");
            //如果不为空，则根据关键词搜索
            if(StringUtil.isNotEmpty(keywords)){
                //builder.withQuery(QueryBuilders.queryStringQuery(keywords).field("name"));
                //1:关键字查询
                boolQueryBuilder.must(QueryBuilders.matchQuery("name",searchMap.get("keywords")));
            }
            //2:分类搜索
            if(StringUtils.isNotEmpty(searchMap.get("category"))){
                boolQueryBuilder.must(QueryBuilders.matchQuery("categoryName",searchMap.get("category")));
            }
            //3：品牌搜索
            if(StringUtils.isNotEmpty(searchMap.get("brand"))){
                boolQueryBuilder.must(QueryBuilders.matchQuery("brandName",searchMap.get("brand")));
            }

            //4：规格搜索
            for (Map.Entry<String, String> entry : searchMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if(key.startsWith("spec_")){
                    boolQueryBuilder.must(QueryBuilders.termQuery("specMap."+key.split("_")[1]+".keyword",value));
                }
            }

        }

        //分页实现搜索
        Integer pageNum = getPageInfo(searchMap).get("pageNum");//指定排序的域
        Integer pageSize = getPageInfo(searchMap).get("pageSize");//指定排序的规则
        builder.withPageable(PageRequest.of(pageNum-1,pageSize));

        //排序的实现
        String sortField = searchMap.get("sortField");
        String sortRule = searchMap.get("sortRule");
        if(StringUtils.isNotEmpty(sortField) && StringUtils.isNotEmpty(sortRule)){
            builder.withSort(new FieldSortBuilder(sortField).order(SortOrder.valueOf(sortRule)));
        }
        builder.withQuery(boolQueryBuilder);
        return builder;
    }


    public Map<String,Integer> getPageInfo(Map<String,String> searchMap){
        Map<String,Integer> pageInfo = new HashMap<>();
        String pageNum = searchMap.get("pageNum");
        String pageSize = searchMap.get("pageSize");
        if(StringUtils.isNotEmpty(pageNum) && StringUtils.isNotEmpty(pageSize)){
            pageInfo.put("pageNum",Integer.parseInt(pageNum));
            pageInfo.put("pageSize",Integer.parseInt(pageSize));
        }else{
            pageInfo.put("pageNum",1);
            pageInfo.put("pageSize",5);
        }
        return pageInfo;
    }

    /**
     * 数据搜索结果集搜索
     * @param builder
     * @return
     */
    private Map<String, Object> searchList(NativeSearchQueryBuilder builder) {

        //高亮配置
        HighlightBuilder.Field field = new HighlightBuilder.Field("name");//指定高亮域
        //前缀
        field.preTags("<em style=\"color:red;\">");
        //后缀
        field.postTags("</em>");
        //碎片长度，关键词数据的长度
        field.fragmentOffset(1000);
        //添加高亮
        builder.withHighlightFields(field);

        /**
         * 执行搜索
         * 1 搜索条件封装对象
         * 2 搜索的结果集（集合数据）需要转换的类型
         * 3 AggregatedPage<SkuInfo>:搜索结果集的封装
         */
       //AggregatedPage<SkuInfo> page = elasticsearchTemplate.queryForPage(builder.build(), SkuInfo.class);

        AggregatedPage<SkuInfo> page = elasticsearchTemplate.queryForPage(builder.build(),//搜索条件封装
                SkuInfo.class,//数据集合要转换的类型的字节码
                //SearchResultMapper 执行搜索后，将数据结果集封装到该对象中
                new SearchResultMapper() {
                    @Override
                    public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {

                        List<T> list  = new ArrayList<>();
                        //执行查询，获取所有数据-》结果集【高亮|非高亮】
                        for (SearchHit hit : searchResponse.getHits()) {
                            //获取非高亮数据
                            SkuInfo skuInfo = JSON.parseObject(hit.getSourceAsString(), SkuInfo.class);
                            //获取高亮数据
                            HighlightField highlightField = hit.getHighlightFields().get("name");
                            if(highlightField != null && highlightField.getFragments()!= null){
                                //读取高亮数据
                                Text[] fragments = highlightField.getFragments();
                                StringBuffer stringBuffer = new StringBuffer();
                                for (Text fragment : fragments) {
                                    stringBuffer.append(fragment);
                                }
                                skuInfo.setName(stringBuffer.toString());
                                list.add((T) skuInfo);
                            }
                        }
                        //将数据返回
                        /****
                         * 1）搜索的集合数据带高亮
                         * 2）分页信息对象
                         * 3）搜索记录总条数
                         */
                        return new AggregatedPageImpl<T>(list,pageable,searchResponse.getHits().getTotalHits());
                    }
                });
        //结果接集合内容
        List<SkuInfo> content = page.getContent();
        //结果集总记录数
        long totalElements = page.getTotalElements();
        //结果集总页数
        int totalPages = page.getTotalPages();


        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("rows",content);
        resultMap.put("size",totalElements);
        resultMap.put("totalPage",totalPages);
        return resultMap;
    }

    /**
     * 分组查询
     */
    private List<String> searchFieldList(NativeSearchQueryBuilder builder,String aliasName,String fieldName) {
        /**
         * 分组查询域集合
         * addAggregation 添加一个集合
         * 1 取别名
         * 2 表示根据哪个域进行分组查询
         */
        builder.addAggregation(AggregationBuilders.terms(aliasName).field(fieldName).size(10000));
        AggregatedPage<SkuInfo> aggregatedPage = elasticsearchTemplate.queryForPage(builder.build(), SkuInfo.class);

        /**
         * 获取分组数据
         * aggregatedPage.getAggregations()获取的是集合，可以根据多个域进行分组
         */
        StringTerms stringTerms = aggregatedPage.getAggregations().get(aliasName);

        List<String> fieldList = new ArrayList<String>();
        for (StringTerms.Bucket bucket : stringTerms.getBuckets()) {
            String FieldName = bucket.getKeyAsString();//其中一个域的名称
            fieldList.add(FieldName);
        }
        return fieldList;
    }

    /**
     * 导入索引库
     */
    @Override
    public void importData() {
        //1:feign调用，查询List<Sku>
        CommonResult<List<Sku>> skuList = skuFeign.findAll();
        //2:将List<Sku>转成List<SkuInfo>
        List<SkuInfo> skuInfoList = JSON.parseArray(JSON.toJSONString(skuList.getData()), SkuInfo.class);
        //生成动态域
        for (SkuInfo skuInfo : skuInfoList) {
            //将json格式的数据转为map对象
            Map<String,Object> specMap = JSON.parseObject(skuInfo.getSpec(), Map.class);
            skuInfo.setSpecMap(specMap);
        }
        //3:调用dao实现数据批量导入
        skuEsMapper.saveAll(skuInfoList);
    }
}
