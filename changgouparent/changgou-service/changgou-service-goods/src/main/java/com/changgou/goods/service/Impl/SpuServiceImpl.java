package com.changgou.goods.service.Impl;

import com.alibaba.fastjson.JSON;
import com.changgou.entity.IdWorker;
import com.changgou.enums.GoodsAuditEnum;
import com.changgou.enums.GoodsStatusEnum;
import com.changgou.exception.ApiException;
import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.dao.CategoryMapper;
import com.changgou.goods.dao.SkuMapper;
import com.changgou.goods.dao.SpuMapper;
import com.changgou.goods.dto.Goods;
import com.changgou.goods.pojo.*;
import com.changgou.goods.service.SpuService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:Spu业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private SkuMapper skuMapper;


    /**
     * Spu条件+分页查询
     * @param spu 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public List<Spu> findPage(Spu spu, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(spu);
        //执行搜索
        return spuMapper.selectByExample(example);
    }

    /**
     * Spu分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Spu> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return spuMapper.selectAll();
    }

    /**
     * Spu条件查询
     * @param spu
     * @return
     */
    @Override
    public List<Spu> findList(Spu spu){
        //构建查询条件
        Example example = createExample(spu);
        //根据构建的条件查询数据
        return spuMapper.selectByExample(example);
    }


    /**
     * Spu构建查询对象
     * @param spu
     * @return
     */
    public Example createExample(Spu spu){
        Example example=new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if(spu!=null){
            // 主键
            if(!StringUtils.isEmpty(spu.getId())){
                    criteria.andEqualTo("id",spu.getId());
            }
            // 货号
            if(!StringUtils.isEmpty(spu.getSn())){
                    criteria.andEqualTo("sn",spu.getSn());
            }
            // SPU名
            if(!StringUtils.isEmpty(spu.getName())){
                    criteria.andLike("name","%"+spu.getName()+"%");
            }
            // 副标题
            if(!StringUtils.isEmpty(spu.getCaption())){
                    criteria.andEqualTo("caption",spu.getCaption());
            }
            // 品牌ID
            if(!StringUtils.isEmpty(spu.getBrandId())){
                    criteria.andEqualTo("brandId",spu.getBrandId());
            }
            // 一级分类
            if(!StringUtils.isEmpty(spu.getCategory1Id())){
                    criteria.andEqualTo("category1Id",spu.getCategory1Id());
            }
            // 二级分类
            if(!StringUtils.isEmpty(spu.getCategory2Id())){
                    criteria.andEqualTo("category2Id",spu.getCategory2Id());
            }
            // 三级分类
            if(!StringUtils.isEmpty(spu.getCategory3Id())){
                    criteria.andEqualTo("category3Id",spu.getCategory3Id());
            }
            // 模板ID
            if(!StringUtils.isEmpty(spu.getTemplateId())){
                    criteria.andEqualTo("templateId",spu.getTemplateId());
            }
            // 运费模板id
            if(!StringUtils.isEmpty(spu.getFreightId())){
                    criteria.andEqualTo("freightId",spu.getFreightId());
            }
            // 图片
            if(!StringUtils.isEmpty(spu.getImage())){
                    criteria.andEqualTo("image",spu.getImage());
            }
            // 图片列表
            if(!StringUtils.isEmpty(spu.getImages())){
                    criteria.andEqualTo("images",spu.getImages());
            }
            // 售后服务
            if(!StringUtils.isEmpty(spu.getSaleService())){
                    criteria.andEqualTo("saleService",spu.getSaleService());
            }
            // 介绍
            if(!StringUtils.isEmpty(spu.getIntroduction())){
                    criteria.andEqualTo("introduction",spu.getIntroduction());
            }
            // 规格列表
            if(!StringUtils.isEmpty(spu.getSpecItems())){
                    criteria.andEqualTo("specItems",spu.getSpecItems());
            }
            // 参数列表
            if(!StringUtils.isEmpty(spu.getParaItems())){
                    criteria.andEqualTo("paraItems",spu.getParaItems());
            }
            // 销量
            if(!StringUtils.isEmpty(spu.getSaleNum())){
                    criteria.andEqualTo("saleNum",spu.getSaleNum());
            }
            // 评论数
            if(!StringUtils.isEmpty(spu.getCommentNum())){
                    criteria.andEqualTo("commentNum",spu.getCommentNum());
            }
            // 是否上架,0已下架，1已上架
            if(!StringUtils.isEmpty(spu.getIsMarketable())){
                    criteria.andEqualTo("isMarketable",spu.getIsMarketable());
            }
            // 是否启用规格
            if(!StringUtils.isEmpty(spu.getIsEnableSpec())){
                    criteria.andEqualTo("isEnableSpec",spu.getIsEnableSpec());
            }
            // 是否删除,0:未删除，1：已删除
            if(!StringUtils.isEmpty(spu.getIsDelete())){
                    criteria.andEqualTo("isDelete",spu.getIsDelete());
            }
            // 审核状态，0：未审核，1：已审核，2：审核不通过
            if(!StringUtils.isEmpty(spu.getStatus())){
                    criteria.andEqualTo("status",spu.getStatus());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public int delete(Long id){
        int count = spuMapper.deleteByPrimaryKey(id);
        if(count>0){
            return count;
        }else{
            throw new ApiException("操作失败");
        }
    }

    /**
     * 修改Spu
     * @param spu
     */
    @Override
    public int update(Spu spu){
        int count = spuMapper.updateByPrimaryKey(spu);
        if(count>0){
            return count;
        }else{
            throw new ApiException("操作失败");
        }
    }

    /**
     * 增加Spu
     * @param spu
     */
    @Override
    public int add(Spu spu){
        int count = spuMapper.insert(spu);
        if(count>0){
            return count;
        }else{
            throw new ApiException("操作失败");
        }
    }

    /**
     * 根据ID查询Spu
     * @param id
     * @return
     */
    @Override
    public Spu findById(Long id){
        return  spuMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Spu全部数据
     * @return
     */
    @Override
    public List<Spu> findAll() {
        return spuMapper.selectAll();
    }

    /**
     * 添加商品
     * @param goods
     * @return
     */
    @Override
    public int createGoods(Goods goods) {
        Spu spu = goods.getSpu();
        Long id = spu.getId();
        if(id != null){
            spuMapper.updateByPrimaryKeySelective(spu);
            Sku sku = new Sku();
            sku.setSpuId(spu.getId());
            skuMapper.delete(sku);
        }else{
            Integer category3Id = spu.getCategory3Id();
            Category category = categoryMapper.selectByPrimaryKey(category3Id);
            List<Sku> skuList = goods.getSkuList();
            Brand brand = brandMapper.selectByPrimaryKey(spu.getBrandId());

            spu.setId(idWorker.nextId());
            spuMapper.insertSelective(spu);

            Date date = new Date();
            int count=0;
            for (Sku sku : skuList) {
                //构建SKU名称，采用SPU+规格值组装
                if(StringUtils.isEmpty(sku.getSpec())){
                    sku.setSpec("{}");
                }
                //获取Spu的名字
                String name = spu.getName();
                //将规格转换成Map
                Map<String,String> specMap = JSON.parseObject(sku.getSpec(), Map.class);
                //循环组装Sku的名字
                for (Map.Entry<String, String> entry : specMap.entrySet()) {
                    name+="  "+entry.getValue();
                }
                sku.setId(idWorker.nextId());
                sku.setCategoryId(category.getId());
                sku.setCategoryName(category.getName());
                sku.setBrandName(brand.getName());
                sku.setCreateTime(date);
                sku.setUpdateTime(date);
                sku.setSpuId(spu.getId());
                sku.setName(name);
                count = skuMapper.insert(sku);
            }
            return count;
        }
       return 0;
    }

    /**
     * 根据spuId查询商品
     * @param spuId
     * @return
     */
    @Override
    public Goods findGoodsBySpuId(Long spuId) {
        Goods goods = new Goods();
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        goods.setSpu(spu);
        Sku sku = new Sku();
        sku.setSpuId(spu.getId());
        List<Sku> skuList = skuMapper.select(sku);
        goods.setSkuList(skuList);
        return goods;
    }

    /**
     * 商品审核-通过
     * @param spuId
     * @return
     */
    @Override
    public int audit(Long spuId) {
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        String isDelete = spu.getIsDelete();
        if(("1").equalsIgnoreCase(isDelete)){
            throw new ApiException("该商品已删除");
        }else{
            spu.setStatus(GoodsAuditEnum.APPROVED.getValue());
            spu.setIsMarketable(GoodsStatusEnum.SHELVES.getValue());
            int count = spuMapper.updateByPrimaryKeySelective(spu);
            return count;
        }
    }

    /**
     * 商品下架
     * @param spuId
     * @return
     */
    @Override
    public int undercarriage(Long spuId) {
        Spu spu = spuMapper.selectByPrimaryKey(spuId);

        if(("1").equalsIgnoreCase(spu.getIsDelete())){
            throw new ApiException("已删除的商品不能上架");
        }
        if(!(GoodsAuditEnum.APPROVED.getValue()).equalsIgnoreCase(spu.getStatus())){
            throw new ApiException("未审核和审核不通过的商品不能上架");
        }
        spu.setIsMarketable(GoodsStatusEnum.SHELVES.getValue());
        int count = spuMapper.updateByPrimaryKeySelective(spu);
        return count;
    }

    /**
     * 商品下架
     * @param spuId
     * @return
     */
    @Override
    public int shelves(Long spuId) {
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        if(("1").equalsIgnoreCase(spu.getIsDelete())){
            throw new ApiException("已删除的产品不能下架");
        }
        spu.setIsMarketable(GoodsStatusEnum.UNDERCARRIAGE.getValue());
        int count = spuMapper.updateByPrimaryKeySelective(spu);
        return count;
    }

    /**
     * 批量上架
     * @param ids
     * @return
     */
    @Override
    public int putMany(List<Long> ids) {
        for (Long id : ids) {
            Spu spu = spuMapper.selectByPrimaryKey(id);
            if((GoodsStatusEnum.SHELVES.getValue()).equalsIgnoreCase(spu.getIsMarketable())){
                throw new ApiException("已上架的商品不能再次上架");
            }
        }
        Spu spu = new Spu();
        spu.setIsMarketable(GoodsStatusEnum.SHELVES.getValue());

        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("isDelete","0");
        criteria.andEqualTo("isMarketable",GoodsStatusEnum.UNDERCARRIAGE.getValue());
        criteria.andEqualTo("status",GoodsAuditEnum.APPROVED.getValue());

        criteria.andIn("id",ids);

        int count = spuMapper.updateByExampleSelective(spu, example);

        return count;
    }

    /**
     * 批量下架
     * @param ids
     * @return
     */
    @Override
    public int pushMany(List<Long> ids) {

        for (Long id : ids) {
            Spu spu = spuMapper.selectByPrimaryKey(id);
            if((GoodsStatusEnum.UNDERCARRIAGE.getValue()).equalsIgnoreCase(spu.getIsMarketable())){
                throw new ApiException("已下架的商品不能再次下架");
            }
        }

        Spu spu = new Spu();
        spu.setIsMarketable(GoodsStatusEnum.UNDERCARRIAGE.getValue());

        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("isDelete","0");
        criteria.andEqualTo("isMarketable",GoodsStatusEnum.SHELVES.getValue());
        criteria.andEqualTo("status",GoodsAuditEnum.APPROVED.getValue());

        criteria.andIn("id",ids);

        int count = spuMapper.updateByExampleSelective(spu, example);
        return count;
    }
}
