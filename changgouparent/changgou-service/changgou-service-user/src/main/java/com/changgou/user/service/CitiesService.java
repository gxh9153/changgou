package com.changgou.user.service;
import com.changgou.user.pojo.Cities;

import java.util.List;

/****
 * @Author:gxh
 * @Description:User业务层接口
 * @Date 2021/1/12
 *****/
public interface CitiesService {

    /***
     * Cities多条件分页查询
     * @param cities
     * @param page
     * @param size
     * @return
     */
    List<Cities> findPage(Cities cities, int page, int size);

    /***
     * Cities分页查询
     * @param page
     * @param size
     * @return
     */
    List<Cities> findPage(int page, int size);

    /***
     * Cities多条件搜索方法
     * @param cities
     * @return
     */
    List<Cities> findList(Cities cities);

    /***
     * 删除Cities
     * @param id
     */
    int delete(String id);

    /***
     * 修改Cities数据
     * @param cities
     */
    int update(Cities cities);

    /***
     * 新增Cities
     * @param cities
     */
    int add(Cities cities);

    /**
     * 根据ID查询Cities
     * @param id
     * @return
     */
     Cities findById(String id);

    /***
     * 查询所有Cities
     * @return
     */
    List<Cities> findAll();
}
