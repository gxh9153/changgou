package com.changgou.goods.service;

import com.changgou.goods.dto.Goods;
import com.changgou.goods.pojo.Spu;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Spu业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface SpuService {

    /***
     * Spu多条件分页查询
     * @param spu
     * @param page
     * @param size
     * @return
     */
    List<Spu> findPage(Spu spu, int page, int size);

    /***
     * Spu分页查询
     * @param page
     * @param size
     * @return
     */
    List<Spu> findPage(int page, int size);

    /***
     * Spu多条件搜索方法
     * @param spu
     * @return
     */
    List<Spu> findList(Spu spu);

    /***
     * 删除Spu
     * @param id
     */
    int delete(Long id);

    /***
     * 修改Spu数据
     * @param spu
     */
    int update(Spu spu);

    /***
     * 新增Spu
     * @param spu
     */
    int add(Spu spu);

    /**
     * 根据ID查询Spu
     * @param id
     * @return
     */
     Spu findById(Long id);

    /***
     * 查询所有Spu
     * @return
     */
    List<Spu> findAll();

    /**
     * 添加商品
     * @param goods
     * @return
     */
    int createGoods(Goods goods);

    /**
     * 根据spuId查询商品
     * @param spuId
     * @return
     */
    Goods findGoodsBySpuId(Long spuId);

    /**
     * 审核商品
     * @param spuId
     * @return
     */
    int audit(Long spuId);

    /**
     * 下架商品
     * @param spuId
     * @return
     */
    int undercarriage(Long spuId);

    /**
     * 上架商品
     * @param spuId
     * @return
     */
    int shelves(Long spuId);

    /**
     * 批量上架
     * @param ids
     * @return
     */
    int putMany(List<Long> ids);

    /**
     * 批量下架
     * @param ids
     * @return
     */
    int pushMany(List<Long> ids);
}
