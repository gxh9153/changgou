package com.changgou.goods.service.Impl;

import com.changgou.exception.ApiException;
import com.changgou.goods.dao.CategoryMapper;
import com.changgou.goods.dao.SpecMapper;
import com.changgou.goods.dao.TemplateMapper;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.pojo.Spec;
import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.SpecService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class SpecServiceImpl implements SpecService {

    @Autowired
    private SpecMapper specMapper;

    @Autowired
    private TemplateMapper templateMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Spec> findPage(Spec spec, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(spec);
        List<Spec> list = specMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<Spec> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        List<Spec> list = specMapper.selectAll();
        return list;
    }

    @Override
    public List<Spec> findList(Spec spec) {
        Example example = createExample(spec);
        List<Spec> list = specMapper.selectByExample(example);
        return list;
    }

    @Override
    public int delete(Integer id) {
        Spec spec = specMapper.selectByPrimaryKey(id);
        Integer templateId = spec.getTemplateId();
        Template template = templateMapper.selectByPrimaryKey(templateId);
        Integer specNum = template.getSpecNum();
        if(specNum != null && specNum>0){
            updateSpecNum(spec,-1);
        }
        int count = specMapper.deleteByPrimaryKey(id);
        if(count>0){
            return count;
        }else{
            throw  new ApiException("操作失败");
        }
    }

    @Override
    public int update(Spec spec) {
        int count = specMapper.updateByPrimaryKeySelective(spec);
        if(count>0){
            return count;
        }else{
            throw new ApiException("操作失败");
        }
    }

    @Override
    public int add(Spec spec) {
        updateSpecNum(spec,1);
        int count = specMapper.insertSelective(spec);
        if(count>0){
            return count;
        }else{
            throw new ApiException("操作失败");
        }
    }

    @Override
    public Spec findById(Integer id) {
        Spec spec = specMapper.selectByPrimaryKey(id);
        return spec;
    }

    @Override
    public List<Spec> findAll() {
        List<Spec> list = specMapper.selectAll();
        return list;
    }

    @Override
    public List<Spec> findByCategory(Integer categoryId) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        Spec spec = new Spec();
        spec.setTemplateId(category.getTemplateId());
        List<Spec> list = specMapper.select(spec);
        return list;
    }

    public Example createExample(Spec spec){
        Example example=new Example(Spec.class);
        Example.Criteria criteria = example.createCriteria();
        if(spec!=null){
            // ID
            if(!StringUtils.isEmpty(spec.getId())){
                criteria.andEqualTo("id",spec.getId());
            }
            // 名称
            if(!StringUtils.isEmpty(spec.getName())){
                criteria.andLike("name","%"+spec.getName()+"%");
            }
            // 规格选项
            if(!StringUtils.isEmpty(spec.getOptions())){
                criteria.andEqualTo("options",spec.getOptions());
            }
            // 排序
            if(!StringUtils.isEmpty(spec.getSeq())){
                criteria.andEqualTo("seq",spec.getSeq());
            }
            // 模板ID
            if(!StringUtils.isEmpty(spec.getTemplateId())){
                criteria.andEqualTo("templateId",spec.getTemplateId());
            }
        }
        return example;
    }

    /**
     * 修改模板统计数据
     * @param spec:操作的模板
     * @param count:变更的数量
     */
    public void updateSpecNum(Spec spec,int count){
        //修改模板数量统计
        Template template = templateMapper.selectByPrimaryKey(spec.getTemplateId());
        template.setSpecNum(template.getSpecNum()+count);
        templateMapper.updateByPrimaryKeySelective(template);
    }
}
