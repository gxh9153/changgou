package com.changgou.orders.service;
import com.changgou.orders.pojo.Order;

import java.util.List;

/****
 * @Author:gxh
 * @Description:Order业务层接口
 * @Date 2021/1/12
 *****/
public interface OrderService {

    /***
     * Order多条件分页查询
     * @param order
     * @param page
     * @param size
     * @return
     */
    List<Order> findPage(Order order, int page, int size);

    /***
     * Order分页查询
     * @param page
     * @param size
     * @return
     */
    List<Order> findPage(int page, int size);

    /***
     * Order多条件搜索方法
     * @param order
     * @return
     */
    List<Order> findList(Order order);

    /***
     * 删除Order
     * @param id
     */
    int delete(String id);

    /***
     * 修改Order数据
     * @param order
     */
    int update(Order order);

    /***
     * 新增Order
     * @param order
     */
    int add(Order order);

    /**
     * 根据ID查询Order
     * @param id
     * @return
     */
     Order findById(String id);

    /***
     * 查询所有Order
     * @return
     */
    List<Order> findAll();
}
