package com.changgou.orders.service;
import com.changgou.orders.pojo.CategoryReport;

import java.util.Date;
import java.util.List;

/****
 * @Author:gxh
 * @Description:CategoryReport业务层接口
 * @Date 2021/1/12
 *****/
public interface CategoryReportService {

    /***
     * CategoryReport多条件分页查询
     * @param categoryReport
     * @param page
     * @param size
     * @return
     */
    List<CategoryReport> findPage(CategoryReport categoryReport, int page, int size);

    /***
     * CategoryReport分页查询
     * @param page
     * @param size
     * @return
     */
    List<CategoryReport> findPage(int page, int size);

    /***
     * CategoryReport多条件搜索方法
     * @param categoryReport
     * @return
     */
    List<CategoryReport> findList(CategoryReport categoryReport);

    /***
     * 删除CategoryReport
     * @param id
     */
    int delete(Date id);

    /***
     * 修改CategoryReport数据
     * @param categoryReport
     */
    int update(CategoryReport categoryReport);

    /***
     * 新增CategoryReport
     * @param categoryReport
     */
    int add(CategoryReport categoryReport);

    /**
     * 根据ID查询CategoryReport
     * @param id
     * @return
     */
     CategoryReport findById(Date id);

    /***
     * 查询所有CategoryReport
     * @return
     */
    List<CategoryReport> findAll();
}
