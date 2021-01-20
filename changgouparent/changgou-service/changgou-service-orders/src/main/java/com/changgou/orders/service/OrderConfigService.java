package com.changgou.orders.service;
import com.changgou.orders.pojo.OrderConfig;

import java.util.List;

/****
 * @Author:gxh
 * @Description:OrderConfig业务层接口
 * @Date 2021/1/12
 *****/
public interface OrderConfigService {

    /***
     * OrderConfig多条件分页查询
     * @param orderConfig
     * @param page
     * @param size
     * @return
     */
    List<OrderConfig> findPage(OrderConfig orderConfig, int page, int size);

    /***
     * OrderConfig分页查询
     * @param page
     * @param size
     * @return
     */
    List<OrderConfig> findPage(int page, int size);

    /***
     * OrderConfig多条件搜索方法
     * @param orderConfig
     * @return
     */
    List<OrderConfig> findList(OrderConfig orderConfig);

    /***
     * 删除OrderConfig
     * @param id
     */
    int delete(Integer id);

    /***
     * 修改OrderConfig数据
     * @param orderConfig
     */
    int update(OrderConfig orderConfig);

    /***
     * 新增OrderConfig
     * @param orderConfig
     */
    int add(OrderConfig orderConfig);

    /**
     * 根据ID查询OrderConfig
     * @param id
     * @return
     */
     OrderConfig findById(Integer id);

    /***
     * 查询所有OrderConfig
     * @return
     */
    List<OrderConfig> findAll();
}
