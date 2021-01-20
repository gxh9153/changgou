package com.changgou.user.service.impl;

import com.changgou.user.dao.CitiesMapper;
import com.changgou.user.pojo.Cities;
import com.changgou.user.service.CitiesService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:gxh
 * @Description:Address业务层接口实现类
 * @Date 2021/1/12
 *****/
@Service
public class CitiesServiceImpl implements CitiesService {

    @Autowired
    private CitiesMapper citiesMapper;


    /**
     * Cities条件+分页查询
     * @param cities 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public List<Cities> findPage(Cities cities, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(cities);
        //执行搜索
        return citiesMapper.selectByExample(example);
    }

    /**
     * Cities分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Cities> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return citiesMapper.selectAll();
    }

    /**
     * Cities条件查询
     * @param cities
     * @return
     */
    @Override
    public List<Cities> findList(Cities cities){
        //构建查询条件
        Example example = createExample(cities);
        //根据构建的条件查询数据
        return citiesMapper.selectByExample(example);
    }


    /**
     * Cities构建查询对象
     * @param cities
     * @return
     */
    public Example createExample(Cities cities){
        Example example=new Example(Cities.class);
        Example.Criteria criteria = example.createCriteria();
        if(cities!=null){
            // 城市ID
            if(!StringUtils.isEmpty(cities.getCityid())){
                    criteria.andEqualTo("cityid",cities.getCityid());
            }
            // 城市名称
            if(!StringUtils.isEmpty(cities.getCity())){
                    criteria.andEqualTo("city",cities.getCity());
            }
            // 省份ID
            if(!StringUtils.isEmpty(cities.getProvinceid())){
                    criteria.andEqualTo("provinceid",cities.getProvinceid());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public int delete(String id){
        int count = citiesMapper.deleteByPrimaryKey(id);
        return count;
    }

    /**
     * 修改Cities
     * @param cities
     */
    @Override
    public int update(Cities cities){
        int count = citiesMapper.updateByPrimaryKey(cities);
        return count;
    }

    /**
     * 增加Cities
     * @param cities
     */
    @Override
    public int add(Cities cities){
        int count = citiesMapper.insert(cities);
        return count;
    }

    /**
     * 根据ID查询Cities
     * @param id
     * @return
     */
    @Override
    public Cities findById(String id){
        return  citiesMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Cities全部数据
     * @return
     */
    @Override
    public List<Cities> findAll() {
        return citiesMapper.selectAll();
    }
}
