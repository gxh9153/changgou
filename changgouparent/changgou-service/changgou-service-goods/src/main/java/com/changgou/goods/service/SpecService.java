package com.changgou.goods.service;

import com.changgou.goods.pojo.Spec;

import java.util.List;

public interface SpecService {
    /***
     * Spec多条件分页查询
     * @param spec
     * @param page
     * @param size
     * @return
     */
    List<Spec> findPage(Spec spec, int page, int size);
    /***
     * Spec分页查询
     * @param page
     * @param size
     * @return
     */
    List<Spec> findPage(int page, int size);
    /***
     * Spec多条件搜索方法
     * @param spec
     * @return
     */
    List<Spec> findList(Spec spec);
    /***
     * 删除Spec
     * @param id
     */
    int delete(Integer id);
    /***
     * 修改Spec数据
     * @param spec
     */
    int update(Spec spec);
    /***
     * 新增Spec
     * @param spec
     */
    int add(Spec spec);
    /**
     * 根据ID查询Spec
     * @param id
     * @return
     */
    Spec findById(Integer id);
    /***
     * 查询所有Spec
     * @return
     */
    List<Spec> findAll();

    /**
     * 根据分类id查询规格列表
     * @param categoryId
     * @return
     */
    List<Spec> findByCategory(Integer categoryId);
}
