package com.changgou.user.service;
import com.changgou.user.pojo.Provinces;

import java.util.List;

/****
 * @Author:gxh
 * @Description:User业务层接口
 * @Date 2021/1/12
 *****/
public interface ProvincesService {

    /***
     * Provinces多条件分页查询
     * @param provinces
     * @param page
     * @param size
     * @return
     */
    List<Provinces> findPage(Provinces provinces, int page, int size);

    /***
     * Provinces分页查询
     * @param page
     * @param size
     * @return
     */
    List<Provinces> findPage(int page, int size);

    /***
     * Provinces多条件搜索方法
     * @param provinces
     * @return
     */
    List<Provinces> findList(Provinces provinces);

    /***
     * 删除Provinces
     * @param id
     */
    int delete(String id);

    /***
     * 修改Provinces数据
     * @param provinces
     */
    int update(Provinces provinces);

    /***
     * 新增Provinces
     * @param provinces
     */
    int add(Provinces provinces);

    /**
     * 根据ID查询Provinces
     * @param id
     * @return
     */
     Provinces findById(String id);

    /***
     * 查询所有Provinces
     * @return
     */
    List<Provinces> findAll();
}
