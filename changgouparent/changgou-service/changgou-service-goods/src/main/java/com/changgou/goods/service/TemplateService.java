package com.changgou.goods.service;

import com.changgou.goods.pojo.Template;

import java.util.List;

public interface TemplateService {

    /***
     * Template多条件分页查询
     * @param template
     * @param page
     * @param size
     * @return
     */
    List<Template> findPage(Template template, int page, int size);
    /***
     * Template分页查询
     * @param page
     * @param size
     * @return
     */
    List<Template> findPage(int page, int size);
    /***
     * Template多条件搜索方法
     * @param template
     * @return
     */
    List<Template> findList(Template template);
    /***
     * 删除Template
     * @param id
     */
    int delete(Integer id);
    /***
     * 修改Template数据
     * @param template
     */
    int update(Template template);
    /***
     * 新增Template
     * @param template
     */
    int add(Template template);
    /**
     * 根据ID查询Template
     * @param id
     * @return
     */
    Template findById(Integer id);
    /***
     * 查询所有Template
     * @return
     */
    List<Template> findAll();
}
