package com.changgou.orders.service;

import com.changgou.orders.pojo.OrderItem;

import java.util.List;

/**
 * @Author:gxh
 * @Date: 2021/1/18 14:07
 */
public interface CartService {

    /**
     * 添加购物车
     * @param id
     * @param num
     * @param username
     */
    void add(Long id, Integer num, String username);

    /**
     * 获取购物车数据
     * @param username
     * @return
     */
    List<OrderItem> list(String username);
}
