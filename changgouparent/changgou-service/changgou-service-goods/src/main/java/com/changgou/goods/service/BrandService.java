package com.changgou.goods.service;

import com.changgou.goods.pojo.Brand;

import java.util.List;

public interface BrandService {

    /**
     * 查询所有品牌
     * @return
     */
    List<Brand> findAll();

    /**
     * 根据id查询品牌
     * @param id
     * @return
     */
    Brand findById(Integer id);

    /**
     * 添加品牌
     * @param brand
     */
    int createBrand(Brand brand);

    /**
     * 根据id修改品牌
     * @param brand
     * @return
     */
    int updateBrand(Brand brand);

    /**
     * 根据id删除品牌
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 根据条件查询品牌
     * @param brand
     * @return
     */
    List<Brand> findByKeyWords(Brand brand);

    /**
     * 分页查询品牌
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Brand> findByPage(Integer pageNum, Integer pageSize);

    /**
     * 根据条件分页查询
     * @param pageNum
     * @param pageSize
     * @param brand
     * @return
     */
    List<Brand> findByKwAndPage(Integer pageNum, Integer pageSize,Brand brand);

    List<Brand> findByCategory(Integer categoryId);
}
