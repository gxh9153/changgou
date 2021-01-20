package com.changgou.goods.service.Impl;

import com.changgou.exception.ApiException;
import com.changgou.goods.dao.CategoryMapper;
import com.changgou.goods.dao.ParaMapper;
import com.changgou.goods.dao.TemplateMapper;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.pojo.Para;
import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.ParaService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ParaServiceImpl implements ParaService {

    @Autowired
    private ParaMapper paraMapper;
    @Autowired
    private TemplateMapper templateMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Para> findPage(Para para, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(para);
        List<Para> list = paraMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<Para> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        List<Para> list = paraMapper.selectAll();
        return list;
    }

    @Override
    public List<Para> findList(Para para) {
        Example example = createExample(para);
        List<Para> list = paraMapper.selectByExample(example);
        return list;
    }

    @Override
    public int delete(Integer id) {
        Para para = paraMapper.selectByPrimaryKey(id);
        Integer templateId = para.getTemplateId();
        Template template = templateMapper.selectByPrimaryKey(templateId);
        Integer paraNum = template.getParaNum();
        if(paraNum != null && paraNum >0){
            updateParaNum(para,-1);
        }
        int count = paraMapper.deleteByPrimaryKey(id);
        if(count>0){
            return count;
        }else{
            throw new ApiException("操作失败");
        }
    }

    @Override
    public int update(Para para) {
        int count = paraMapper.updateByPrimaryKeySelective(para);
        if(count >0){
            return count;
        }else{
            throw new ApiException("操作失败");
        }
    }

    @Override
    public int add(Para para) {
        updateParaNum(para,1);
        int count = paraMapper.insertSelective(para);
        if(count>0){
            return count;
        }else{
            throw new ApiException("操作失败");
        }
    }

    @Override
    public Para findById(Integer id) {
        Para para = paraMapper.selectByPrimaryKey(id);
        return para;
    }

    @Override
    public List<Para> findAll() {
        List<Para> list = paraMapper.selectAll();
        return list;
    }

    @Override
    public List<Para> findByCategory(Integer categoryId) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        Para para = new Para();
        para.setTemplateId(category.getTemplateId());
        List<Para> list = paraMapper.select(para);
        return list;
    }

    /**
     * Para构建查询对象
     * @param para
     * @return
     */
    public Example createExample(Para para){
        Example example=new Example(Para.class);
        Example.Criteria criteria = example.createCriteria();
        if(para!=null){
            // id
            if(!StringUtils.isEmpty(para.getId())){
                criteria.andEqualTo("id",para.getId());
            }
            // 名称
            if(!StringUtils.isEmpty(para.getName())){
                criteria.andLike("name","%"+para.getName()+"%");
            }
            // 选项
            if(!StringUtils.isEmpty(para.getOptions())){
                criteria.andEqualTo("options",para.getOptions());
            }
            // 排序
            if(!StringUtils.isEmpty(para.getSeq())){
                criteria.andEqualTo("seq",para.getSeq());
            }
            // 模板ID
            if(!StringUtils.isEmpty(para.getTemplateId())){
                criteria.andEqualTo("templateId",para.getTemplateId());
            }
        }
        return example;
    }
    /**
     * 修改模板统计数据
     * @param para:操作的参数
     * @param count:变更的数量
     */
    public void updateParaNum(Para para, int count){
        //修改模板数量统计
        Template template = templateMapper.selectByPrimaryKey(para.getTemplateId());
        template.setParaNum(template.getParaNum()+count);
        templateMapper.updateByPrimaryKeySelective(template);
    }
}
