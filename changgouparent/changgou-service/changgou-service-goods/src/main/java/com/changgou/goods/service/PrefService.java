package com.changgou.goods.service;

import com.changgou.goods.pojo.Pref;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Pref业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface PrefService {

    /***
     * Pref多条件分页查询
     * @param pref
     * @param page
     * @param size
     * @return
     */
    List<Pref> findPage(Pref pref, int page, int size);

    /***
     * Pref分页查询
     * @param page
     * @param size
     * @return
     */
    List<Pref> findPage(int page, int size);

    /***
     * Pref多条件搜索方法
     * @param pref
     * @return
     */
    List<Pref> findList(Pref pref);

    /***
     * 删除Pref
     * @param id
     */
    int delete(Integer id);

    /***
     * 修改Pref数据
     * @param pref
     */
    int update(Pref pref);

    /***
     * 新增Pref
     * @param pref
     */
    int add(Pref pref);

    /**
     * 根据ID查询Pref
     * @param id
     * @return
     */
     Pref findById(Integer id);

    /***
     * 查询所有Pref
     * @return
     */
    List<Pref> findAll();
}
