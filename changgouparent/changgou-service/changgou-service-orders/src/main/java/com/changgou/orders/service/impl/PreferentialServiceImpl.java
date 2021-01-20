package com.changgou.orders.service.impl;

import com.changgou.orders.dao.PreferentialMapper;
import com.changgou.orders.pojo.Preferential;
import com.changgou.orders.service.PreferentialService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:gxh
 * @Description:Preferential业务层接口实现类
 * @Date 2021/1/12
 *****/
@Service
public class PreferentialServiceImpl implements PreferentialService {

    @Autowired
    private PreferentialMapper preferentialMapper;


    /**
     * Preferential条件+分页查询
     * @param preferential 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public List<Preferential> findPage(Preferential preferential, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(preferential);
        //执行搜索
        return preferentialMapper.selectByExample(example);
    }

    /**
     * Preferential分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Preferential> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return preferentialMapper.selectAll();
    }

    /**
     * Preferential条件查询
     * @param preferential
     * @return
     */
    @Override
    public List<Preferential> findList(Preferential preferential){
        //构建查询条件
        Example example = createExample(preferential);
        //根据构建的条件查询数据
        return preferentialMapper.selectByExample(example);
    }


    /**
     * Preferential构建查询对象
     * @param preferential
     * @return
     */
    public Example createExample(Preferential preferential){
        Example example=new Example(Preferential.class);
        Example.Criteria criteria = example.createCriteria();
        if(preferential!=null){
            // ID
            if(!StringUtils.isEmpty(preferential.getId())){
                    criteria.andEqualTo("id",preferential.getId());
            }
            // 消费金额
            if(!StringUtils.isEmpty(preferential.getBuyMoney())){
                    criteria.andEqualTo("buyMoney",preferential.getBuyMoney());
            }
            // 优惠金额
            if(!StringUtils.isEmpty(preferential.getPreMoney())){
                    criteria.andEqualTo("preMoney",preferential.getPreMoney());
            }
            // 品类ID
            if(!StringUtils.isEmpty(preferential.getCategoryId())){
                    criteria.andEqualTo("categoryId",preferential.getCategoryId());
            }
            // 活动开始日期
            if(!StringUtils.isEmpty(preferential.getStartTime())){
                    criteria.andEqualTo("startTime",preferential.getStartTime());
            }
            // 活动截至日期
            if(!StringUtils.isEmpty(preferential.getEndTime())){
                    criteria.andEqualTo("endTime",preferential.getEndTime());
            }
            // 状态
            if(!StringUtils.isEmpty(preferential.getState())){
                    criteria.andEqualTo("state",preferential.getState());
            }
            // 类型1不翻倍 2翻倍
            if(!StringUtils.isEmpty(preferential.getType())){
                    criteria.andEqualTo("type",preferential.getType());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public int delete(Integer id){
        int count = preferentialMapper.deleteByPrimaryKey(id);
        return count;
    }

    /**
     * 修改Preferential
     * @param preferential
     */
    @Override
    public int update(Preferential preferential){
        int count = preferentialMapper.updateByPrimaryKey(preferential);
        return count;
    }

    /**
     * 增加Preferential
     * @param preferential
     */
    @Override
    public int add(Preferential preferential){
        int count = preferentialMapper.insert(preferential);
        return count;
    }

    /**
     * 根据ID查询Preferential
     * @param id
     * @return
     */
    @Override
    public Preferential findById(Integer id){
        return  preferentialMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Preferential全部数据
     * @return
     */
    @Override
    public List<Preferential> findAll() {
        return preferentialMapper.selectAll();
    }
}
