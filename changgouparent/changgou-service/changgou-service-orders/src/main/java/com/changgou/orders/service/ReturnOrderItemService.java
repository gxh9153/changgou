package com.changgou.orders.service;
import com.changgou.orders.pojo.ReturnOrderItem;

import java.util.List;

/****
 * @Author:gxh
 * @Description:ReturnOrderItem业务层接口
 * @Date 2021/1/12
 *****/
public interface ReturnOrderItemService {

    /***
     * ReturnOrderItem多条件分页查询
     * @param returnOrderItem
     * @param page
     * @param size
     * @return
     */
    List<ReturnOrderItem> findPage(ReturnOrderItem returnOrderItem, int page, int size);

    /***
     * ReturnOrderItem分页查询
     * @param page
     * @param size
     * @return
     */
    List<ReturnOrderItem> findPage(int page, int size);

    /***
     * ReturnOrderItem多条件搜索方法
     * @param returnOrderItem
     * @return
     */
    List<ReturnOrderItem> findList(ReturnOrderItem returnOrderItem);

    /***
     * 删除ReturnOrderItem
     * @param id
     */
    int delete(Long id);

    /***
     * 修改ReturnOrderItem数据
     * @param returnOrderItem
     */
    int update(ReturnOrderItem returnOrderItem);

    /***
     * 新增ReturnOrderItem
     * @param returnOrderItem
     */
    int add(ReturnOrderItem returnOrderItem);

    /**
     * 根据ID查询ReturnOrderItem
     * @param id
     * @return
     */
     ReturnOrderItem findById(Long id);

    /***
     * 查询所有ReturnOrderItem
     * @return
     */
    List<ReturnOrderItem> findAll();
}
