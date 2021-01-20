package com.changgou.orders.service;
import com.changgou.orders.pojo.ReturnOrder;

import java.util.List;

/****
 * @Author:gxh
 * @Description:ReturnOrder业务层接口
 * @Date 2021/1/12
 *****/
public interface ReturnOrderService {

    /***
     * ReturnOrder多条件分页查询
     * @param returnOrder
     * @param page
     * @param size
     * @return
     */
    List<ReturnOrder> findPage(ReturnOrder returnOrder, int page, int size);

    /***
     * ReturnOrder分页查询
     * @param page
     * @param size
     * @return
     */
    List<ReturnOrder> findPage(int page, int size);

    /***
     * ReturnOrder多条件搜索方法
     * @param returnOrder
     * @return
     */
    List<ReturnOrder> findList(ReturnOrder returnOrder);

    /***
     * 删除ReturnOrder
     * @param id
     */
    int delete(Long id);

    /***
     * 修改ReturnOrder数据
     * @param returnOrder
     */
    int update(ReturnOrder returnOrder);

    /***
     * 新增ReturnOrder
     * @param returnOrder
     */
    int add(ReturnOrder returnOrder);

    /**
     * 根据ID查询ReturnOrder
     * @param id
     * @return
     */
     ReturnOrder findById(Long id);

    /***
     * 查询所有ReturnOrder
     * @return
     */
    List<ReturnOrder> findAll();
}
