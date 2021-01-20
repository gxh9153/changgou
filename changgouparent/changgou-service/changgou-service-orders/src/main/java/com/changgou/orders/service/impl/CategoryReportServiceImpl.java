package com.changgou.orders.service.impl;

import com.changgou.orders.dao.CategoryReportMapper;
import com.changgou.orders.pojo.CategoryReport;
import com.changgou.orders.service.CategoryReportService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/****
 * @Author:gxh
 * @Description:CategoryReport业务层接口实现类
 * @Date 2021/1/12
 *****/
@Service
public class CategoryReportServiceImpl implements CategoryReportService {

    @Autowired
    private CategoryReportMapper categoryReportMapper;


    /**
     * CategoryReport条件+分页查询
     * @param categoryReport 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public List<CategoryReport> findPage(CategoryReport categoryReport, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(categoryReport);
        //执行搜索
        return categoryReportMapper.selectByExample(example);
    }

    /**
     * CategoryReport分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<CategoryReport> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return categoryReportMapper.selectAll();
    }

    /**
     * CategoryReport条件查询
     * @param categoryReport
     * @return
     */
    @Override
    public List<CategoryReport> findList(CategoryReport categoryReport){
        //构建查询条件
        Example example = createExample(categoryReport);
        //根据构建的条件查询数据
        return categoryReportMapper.selectByExample(example);
    }


    /**
     * CategoryReport构建查询对象
     * @param categoryReport
     * @return
     */
    public Example createExample(CategoryReport categoryReport){
        Example example=new Example(CategoryReport.class);
        Example.Criteria criteria = example.createCriteria();
        if(categoryReport!=null){
            // 1级分类
            if(!StringUtils.isEmpty(categoryReport.getCategoryId1())){
                    criteria.andEqualTo("categoryId1",categoryReport.getCategoryId1());
            }
            // 2级分类
            if(!StringUtils.isEmpty(categoryReport.getCategoryId2())){
                    criteria.andEqualTo("categoryId2",categoryReport.getCategoryId2());
            }
            // 3级分类
            if(!StringUtils.isEmpty(categoryReport.getCategoryId3())){
                    criteria.andEqualTo("categoryId3",categoryReport.getCategoryId3());
            }
            // 统计日期
            if(!StringUtils.isEmpty(categoryReport.getCountDate())){
                    criteria.andEqualTo("countDate",categoryReport.getCountDate());
            }
            // 销售数量
            if(!StringUtils.isEmpty(categoryReport.getNum())){
                    criteria.andEqualTo("num",categoryReport.getNum());
            }
            // 销售额
            if(!StringUtils.isEmpty(categoryReport.getMoney())){
                    criteria.andEqualTo("money",categoryReport.getMoney());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public int delete(Date id){
        int count = categoryReportMapper.deleteByPrimaryKey(id);
        return count;
    }

    /**
     * 修改CategoryReport
     * @param categoryReport
     */
    @Override
    public int update(CategoryReport categoryReport){
        int count = categoryReportMapper.updateByPrimaryKey(categoryReport);
        return count;
    }

    /**
     * 增加CategoryReport
     * @param categoryReport
     */
    @Override
    public int add(CategoryReport categoryReport){
        int count = categoryReportMapper.insert(categoryReport);
        return count;
    }

    /**
     * 根据ID查询CategoryReport
     * @param id
     * @return
     */
    @Override
    public CategoryReport findById(Date id){
        return  categoryReportMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询CategoryReport全部数据
     * @return
     */
    @Override
    public List<CategoryReport> findAll() {
        return categoryReportMapper.selectAll();
    }
}
