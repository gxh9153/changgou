package com.changgou.orders.service;
import com.changgou.orders.pojo.OrderLog;

import java.util.List;

/****
 * @Author:gxh
 * @Description:OrderLog业务层接口
 * @Date 2021/1/12
 *****/
public interface OrderLogService {

    /***
     * OrderLog多条件分页查询
     * @param orderLog
     * @param page
     * @param size
     * @return
     */
    List<OrderLog> findPage(OrderLog orderLog, int page, int size);

    /***
     * OrderLog分页查询
     * @param page
     * @param size
     * @return
     */
    List<OrderLog> findPage(int page, int size);

    /***
     * OrderLog多条件搜索方法
     * @param orderLog
     * @return
     */
    List<OrderLog> findList(OrderLog orderLog);

    /***
     * 删除OrderLog
     * @param id
     */
    int delete(String id);

    /***
     * 修改OrderLog数据
     * @param orderLog
     */
    int update(OrderLog orderLog);

    /***
     * 新增OrderLog
     * @param orderLog
     */
    int add(OrderLog orderLog);

    /**
     * 根据ID查询OrderLog
     * @param id
     * @return
     */
     OrderLog findById(String id);

    /***
     * 查询所有OrderLog
     * @return
     */
    List<OrderLog> findAll();
}
