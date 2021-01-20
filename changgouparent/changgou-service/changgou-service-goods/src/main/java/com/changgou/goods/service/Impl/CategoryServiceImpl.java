package com.changgou.goods.service.Impl;

import com.changgou.exception.ApiException;
import com.changgou.goods.dao.CategoryMapper;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.service.CategoryService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findPage(Category category, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(category);
        List<Category> list = categoryMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<Category> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        List<Category> list = categoryMapper.selectAll();
        return list;
    }

    @Override
    public List<Category> findList(Category category) {
        List<Category> list = categoryMapper.selectAll();
        return list;
    }

    @Override
    public int delete(Integer id) {
        int count = categoryMapper.deleteByPrimaryKey(id);
        if(count>0){
            return count;
        }else{
            throw new ApiException("操作失败");
        }
    }

    @Override
    public int update(Category category) {
        int count = categoryMapper.updateByPrimaryKeySelective(category);
        if(count>0){
            return count;
        }else{
            throw new ApiException("操作失败");
        }
    }

    @Override
    public int add(Category category) {
        int count = categoryMapper.insertSelective(category);
        if(count>0){
            return count;
        }else{
            throw new ApiException("操作失败");
        }
    }

    @Override
    public Category findById(Integer id) {
        Category category = categoryMapper.selectByPrimaryKey(id);
        return category;
    }

    @Override
    public List<Category> findAll() {
        List<Category> list = categoryMapper.selectAll();
        return list;
    }

    @Override
    public List<Category> findByParentId(Integer pid) {
        Category category = new Category();
        category.setParentId(pid);
        List<Category> list = categoryMapper.select(category);
        return list;
    }
    /**
     * Category构建查询对象
     * @param category
     * @return
     */
    public Example createExample(Category category){
        Example example=new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        if(category!=null){
            // 分类ID
            if(!StringUtils.isEmpty(category.getId())){
                criteria.andEqualTo("id",category.getId());
            }
            // 分类名称
            if(!StringUtils.isEmpty(category.getName())){
                criteria.andLike("name","%"+category.getName()+"%");
            }
            // 商品数量
            if(!StringUtils.isEmpty(category.getGoodsNum())){
                criteria.andEqualTo("goodsNum",category.getGoodsNum());
            }
            // 是否显示
            if(!StringUtils.isEmpty(category.getIsShow())){
                criteria.andEqualTo("isShow",category.getIsShow());
            }
            // 是否导航
            if(!StringUtils.isEmpty(category.getIsMenu())){
                criteria.andEqualTo("isMenu",category.getIsMenu());
            }
            // 排序
            if(!StringUtils.isEmpty(category.getSeq())){
                criteria.andEqualTo("seq",category.getSeq());
            }
            // 上级ID
            if(!StringUtils.isEmpty(category.getParentId())){
                criteria.andEqualTo("parentId",category.getParentId());
            }
            // 模板ID
            if(!StringUtils.isEmpty(category.getTemplateId())){
                criteria.andEqualTo("templateId",category.getTemplateId());
            }
        }
        return example;
    }
}
