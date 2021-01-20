package com.changgou.orders.service;
import com.changgou.orders.pojo.OrderItem;

import java.util.List;

/****
 * @Author:gxh
 * @Description:OrderItem业务层接口
 * @Date 2021/1/12
 *****/
public interface OrderItemService {

    /***
     * OrderItem多条件分页查询
     * @param orderItem
     * @param page
     * @param size
     * @return
     */
    List<OrderItem> findPage(OrderItem orderItem, int page, int size);

    /***
     * OrderItem分页查询
     * @param page
     * @param size
     * @return
     */
    List<OrderItem> findPage(int page, int size);

    /***
     * OrderItem多条件搜索方法
     * @param orderItem
     * @return
     */
    List<OrderItem> findList(OrderItem orderItem);

    /***
     * 删除OrderItem
     * @param id
     */
    int delete(String id);

    /***
     * 修改OrderItem数据
     * @param orderItem
     */
    int update(OrderItem orderItem);

    /***
     * 新增OrderItem
     * @param orderItem
     */
    int add(OrderItem orderItem);

    /**
     * 根据ID查询OrderItem
     * @param id
     * @return
     */
     OrderItem findById(String id);

    /***
     * 查询所有OrderItem
     * @return
     */
    List<OrderItem> findAll();
}
