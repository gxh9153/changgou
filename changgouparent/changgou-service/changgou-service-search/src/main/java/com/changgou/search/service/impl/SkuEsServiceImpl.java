package com.changgou.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.api.CommonResult;
import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.pojo.Sku;
import com.changgou.search.dao.SkuEsMapper;
import com.changgou.search.pojo.SkuInfo;
import com.changgou.search.service.SkuEsService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
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

        if(searchMap != null && searchMap.size() >0){
            //根据关键词搜索
            String keywords = searchMap.get("keywords");
            //如果不为空，则根据关键词搜索
            if(StringUtil.isNotEmpty(keywords)){
                builder.withQuery(QueryBuilders.queryStringQuery(keywords).field("name"));
            }

        }
        return builder;
    }

    /**
     * 数据搜索结果集搜索
     * @param builder
     * @return
     */
    private Map<String, Object> searchList(NativeSearchQueryBuilder builder) {
        /**
         * 执行搜索
         * 1 搜索条件封装对象
         * 2 搜索的结果集（集合数据）需要转换的类型
         * 3 AggregatedPage<SkuInfo>:搜索结果集的封装
         */
        AggregatedPage<SkuInfo> page = elasticsearchTemplate.queryForPage(builder.build(), SkuInfo.class);

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
