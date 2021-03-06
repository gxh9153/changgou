package com.changgou.goods.service;

import com.changgou.goods.pojo.Para;

import java.util.List;

public interface ParaService {
    /***
     * Para多条件分页查询
     * @param para
     * @param page
     * @param size
     * @return
     */
    List<Para> findPage(Para para, int page, int size);
    /***
     * Para分页查询
     * @param page
     * @param size
     * @return
     */
    List<Para> findPage(int page, int size);
    /***
     * Para多条件搜索方法
     * @param para
     * @return
     */
    List<Para> findList(Para para);
    /***
     * 删除Para
     * @param id
     */
    int delete(Integer id);
    /***
     * 修改Para数据
     * @param para
     */
    int update(Para para);
    /***
     * 新增Para
     * @param para
     */
    int add(Para para);
    /**
     * 根据ID查询Para
     * @param id
     * @return
     */
    Para findById(Integer id);
    /***
     * 查询所有Para
     * @return
     */
    List<Para> findAll();

    /**
     * 根据分类id查找参数
     * @param categoryId
     * @return
     */
    List<Para> findByCategory(Integer categoryId);
}
