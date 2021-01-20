package com.changgou.goods.service.Impl;

import com.changgou.exception.ApiException;
import com.changgou.goods.dao.TemplateMapper;
import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.TemplateService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateMapper templateMapper;

    /**
     * 根据条件分页查询模板
     * @param template
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Template> findPage(Template template, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(template);
        List<Template> list = templateMapper.selectByExample(example);
        return list;
    }

    /**
     * 分页查询模板
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Template> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        List<Template> list = templateMapper.selectAll();
        return list;
    }

    /**
     * 根据条件查询模板
     * @param template
     * @return
     */
    @Override
    public List<Template> findList(Template template) {
        Example example = createExample(template);
        List<Template> list = templateMapper.selectByExample(example);
        return list;
    }

    /**
     * 根据id删除模板
     * @param id
     */
    @Override
    public int delete(Integer id) {
        int count = templateMapper.deleteByPrimaryKey(id);
        if(count >0){
            return count;
        }else{
            throw new ApiException("操作失败");
        }
    }


    /**
     * 根据id 更新模板
     * @param template
     * @return
     */
    @Override
    public int update(Template template) {
        int count = templateMapper.updateByPrimaryKeySelective(template);
        if(count>0){
            return count;
        }else{
            throw new ApiException("操作失败");
        }
    }

    /**
     * 新增模板
     * @param template
     * @return
     */
    @Override
    public int add(Template template) {
        int count = templateMapper.insertSelective(template);
        if(count>0){
            return count;
        }else{
            throw new ApiException("操作失败");
        }
    }

    /**
     * 根据id查找模板
     * @param id
     * @return
     */
    @Override
    public Template findById(Integer id) {
        Template template = templateMapper.selectByPrimaryKey(id);
        return template;
    }

    /**
     * 查询全部模板
     * @return
     */
    @Override
    public List<Template> findAll() {
        List<Template> list = templateMapper.selectAll();
        return list;
    }

    /**
     * Template构建查询对象
     * @param template
     * @return
     */
    public Example createExample(Template template){
        Example example=new Example(Template.class);
        Example.Criteria criteria = example.createCriteria();
        if(template!=null){
            // ID
            if(!StringUtils.isEmpty(template.getId())){
                criteria.andEqualTo("id",template.getId());
            }
            // 模板名称
            if(!StringUtils.isEmpty(template.getName())){
                criteria.andLike("name","%"+template.getName()+"%");
            }
            // 规格数量
            if(!StringUtils.isEmpty(template.getSpecNum())){
                criteria.andEqualTo("specNum",template.getSpecNum());
            }
            // 参数数量
            if(!StringUtils.isEmpty(template.getParaNum())){
                criteria.andEqualTo("paraNum",template.getParaNum());
            }
        }
        return example;
    }
}
