package com.changgou.orders.service;
import com.changgou.orders.pojo.Preferential;

import java.util.List;

/****
 * @Author:gxh
 * @Description:Preferential业务层接口
 * @Date 2021/1/12
 *****/
public interface PreferentialService {

    /***
     * Preferential多条件分页查询
     * @param preferential
     * @param page
     * @param size
     * @return
     */
    List<Preferential> findPage(Preferential preferential, int page, int size);

    /***
     * Preferential分页查询
     * @param page
     * @param size
     * @return
     */
    List<Preferential> findPage(int page, int size);

    /***
     * Preferential多条件搜索方法
     * @param preferential
     * @return
     */
    List<Preferential> findList(Preferential preferential);

    /***
     * 删除Preferential
     * @param id
     */
    int delete(Integer id);

    /***
     * 修改Preferential数据
     * @param preferential
     */
    int update(Preferential preferential);

    /***
     * 新增Preferential
     * @param preferential
     */
    int add(Preferential preferential);

    /**
     * 根据ID查询Preferential
     * @param id
     * @return
     */
     Preferential findById(Integer id);

    /***
     * 查询所有Preferential
     * @return
     */
    List<Preferential> findAll();
}
