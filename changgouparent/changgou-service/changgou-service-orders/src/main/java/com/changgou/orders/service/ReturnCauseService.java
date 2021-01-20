package com.changgou.orders.service;
import com.changgou.orders.pojo.ReturnCause;

import java.util.List;

/****
 * @Author:gxh
 * @Description:ReturnCause业务层接口
 * @Date 2021/1/12
 *****/
public interface ReturnCauseService {

    /***
     * ReturnCause多条件分页查询
     * @param returnCause
     * @param page
     * @param size
     * @return
     */
    List<ReturnCause> findPage(ReturnCause returnCause, int page, int size);

    /***
     * ReturnCause分页查询
     * @param page
     * @param size
     * @return
     */
    List<ReturnCause> findPage(int page, int size);

    /***
     * ReturnCause多条件搜索方法
     * @param returnCause
     * @return
     */
    List<ReturnCause> findList(ReturnCause returnCause);

    /***
     * 删除ReturnCause
     * @param id
     */
    int delete(Integer id);

    /***
     * 修改ReturnCause数据
     * @param returnCause
     */
    int update(ReturnCause returnCause);

    /***
     * 新增ReturnCause
     * @param returnCause
     */
    int add(ReturnCause returnCause);

    /**
     * 根据ID查询ReturnCause
     * @param id
     * @return
     */
     ReturnCause findById(Integer id);

    /***
     * 查询所有ReturnCause
     * @return
     */
    List<ReturnCause> findAll();
}
