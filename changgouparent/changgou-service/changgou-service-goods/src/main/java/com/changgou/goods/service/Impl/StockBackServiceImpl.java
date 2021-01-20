package com.changgou.goods.service.Impl;

import com.changgou.exception.ApiException;
import com.changgou.goods.dao.StockBackMapper;
import com.changgou.goods.pojo.StockBack;
import com.changgou.goods.service.StockBackService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:StockBack业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class StockBackServiceImpl implements StockBackService {

    @Autowired
    private StockBackMapper stockBackMapper;


    /**
     * StockBack条件+分页查询
     * @param stockBack 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public List<StockBack> findPage(StockBack stockBack, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(stockBack);
        //执行搜索
        return stockBackMapper.selectByExample(example);
    }

    /**
     * StockBack分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<StockBack> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return stockBackMapper.selectAll();
    }

    /**
     * StockBack条件查询
     * @param stockBack
     * @return
     */
    @Override
    public List<StockBack> findList(StockBack stockBack){
        //构建查询条件
        Example example = createExample(stockBack);
        //根据构建的条件查询数据
        return stockBackMapper.selectByExample(example);
    }


    /**
     * StockBack构建查询对象
     * @param stockBack
     * @return
     */
    public Example createExample(StockBack stockBack){
        Example example=new Example(StockBack.class);
        Example.Criteria criteria = example.createCriteria();
        if(stockBack!=null){
            // 订单id
            if(!StringUtils.isEmpty(stockBack.getOrderId())){
                    criteria.andEqualTo("orderId",stockBack.getOrderId());
            }
            // SKU的id
            if(!StringUtils.isEmpty(stockBack.getSkuId())){
                    criteria.andEqualTo("skuId",stockBack.getSkuId());
            }
            // 回滚数量
            if(!StringUtils.isEmpty(stockBack.getNum())){
                    criteria.andEqualTo("num",stockBack.getNum());
            }
            // 回滚状态
            if(!StringUtils.isEmpty(stockBack.getStatus())){
                    criteria.andEqualTo("status",stockBack.getStatus());
            }
            // 创建时间
            if(!StringUtils.isEmpty(stockBack.getCreateTime())){
                    criteria.andEqualTo("createTime",stockBack.getCreateTime());
            }
            // 回滚时间
            if(!StringUtils.isEmpty(stockBack.getBackTime())){
                    criteria.andEqualTo("backTime",stockBack.getBackTime());
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
        int count = stockBackMapper.deleteByPrimaryKey(id);
        if(count>0){
            return count;
        }else{
            throw new ApiException("操作失败");
        }
    }

    /**
     * 修改StockBack
     * @param stockBack
     */
    @Override
    public int update(StockBack stockBack){
        int count = stockBackMapper.updateByPrimaryKey(stockBack);
        if(count>0){
            return count;
        }else{
            throw new ApiException("操作失败");
        }
    }

    /**
     * 增加StockBack
     * @param stockBack
     */
    @Override
    public int add(StockBack stockBack){
        int count = stockBackMapper.insert(stockBack);
        if(count>0){
            return count;
        }else{
            throw new ApiException("操作失败");
        }
    }

    /**
     * 根据ID查询StockBack
     * @param id
     * @return
     */
    @Override
    public StockBack findById(String id){
        return  stockBackMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询StockBack全部数据
     * @return
     */
    @Override
    public List<StockBack> findAll() {
        return stockBackMapper.selectAll();
    }
}
