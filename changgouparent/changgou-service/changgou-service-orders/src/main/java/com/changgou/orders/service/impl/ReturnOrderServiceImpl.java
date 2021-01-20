package com.changgou.orders.service.impl;

import com.changgou.orders.dao.ReturnOrderMapper;
import com.changgou.orders.pojo.ReturnOrder;
import com.changgou.orders.service.ReturnOrderService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:gxh
 * @Description:ReturnOrder业务层接口实现类
 * @Date 2021/1/12
 *****/
@Service
public class ReturnOrderServiceImpl implements ReturnOrderService {

    @Autowired
    private ReturnOrderMapper returnOrderMapper;


    /**
     * ReturnOrder条件+分页查询
     * @param returnOrder 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public List<ReturnOrder> findPage(ReturnOrder returnOrder, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(returnOrder);
        //执行搜索
        return returnOrderMapper.selectByExample(example);
    }

    /**
     * ReturnOrder分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<ReturnOrder> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return returnOrderMapper.selectAll();
    }

    /**
     * ReturnOrder条件查询
     * @param returnOrder
     * @return
     */
    @Override
    public List<ReturnOrder> findList(ReturnOrder returnOrder){
        //构建查询条件
        Example example = createExample(returnOrder);
        //根据构建的条件查询数据
        return returnOrderMapper.selectByExample(example);
    }


    /**
     * ReturnOrder构建查询对象
     * @param returnOrder
     * @return
     */
    public Example createExample(ReturnOrder returnOrder){
        Example example=new Example(ReturnOrder.class);
        Example.Criteria criteria = example.createCriteria();
        if(returnOrder!=null){
            // 服务单号
            if(!StringUtils.isEmpty(returnOrder.getId())){
                    criteria.andEqualTo("id",returnOrder.getId());
            }
            // 订单号
            if(!StringUtils.isEmpty(returnOrder.getOrderId())){
                    criteria.andEqualTo("orderId",returnOrder.getOrderId());
            }
            // 申请时间
            if(!StringUtils.isEmpty(returnOrder.getApplyTime())){
                    criteria.andEqualTo("applyTime",returnOrder.getApplyTime());
            }
            // 用户ID
            if(!StringUtils.isEmpty(returnOrder.getUserId())){
                    criteria.andEqualTo("userId",returnOrder.getUserId());
            }
            // 用户账号
            if(!StringUtils.isEmpty(returnOrder.getUserAccount())){
                    criteria.andEqualTo("userAccount",returnOrder.getUserAccount());
            }
            // 联系人
            if(!StringUtils.isEmpty(returnOrder.getLinkman())){
                    criteria.andEqualTo("linkman",returnOrder.getLinkman());
            }
            // 联系人手机
            if(!StringUtils.isEmpty(returnOrder.getLinkmanMobile())){
                    criteria.andEqualTo("linkmanMobile",returnOrder.getLinkmanMobile());
            }
            // 类型
            if(!StringUtils.isEmpty(returnOrder.getType())){
                    criteria.andEqualTo("type",returnOrder.getType());
            }
            // 退款金额
            if(!StringUtils.isEmpty(returnOrder.getReturnMoney())){
                    criteria.andEqualTo("returnMoney",returnOrder.getReturnMoney());
            }
            // 是否退运费
            if(!StringUtils.isEmpty(returnOrder.getIsReturnFreight())){
                    criteria.andEqualTo("isReturnFreight",returnOrder.getIsReturnFreight());
            }
            // 申请状态
            if(!StringUtils.isEmpty(returnOrder.getStatus())){
                    criteria.andEqualTo("status",returnOrder.getStatus());
            }
            // 处理时间
            if(!StringUtils.isEmpty(returnOrder.getDisposeTime())){
                    criteria.andEqualTo("disposeTime",returnOrder.getDisposeTime());
            }
            // 退货退款原因
            if(!StringUtils.isEmpty(returnOrder.getReturnCause())){
                    criteria.andEqualTo("returnCause",returnOrder.getReturnCause());
            }
            // 凭证图片
            if(!StringUtils.isEmpty(returnOrder.getEvidence())){
                    criteria.andEqualTo("evidence",returnOrder.getEvidence());
            }
            // 问题描述
            if(!StringUtils.isEmpty(returnOrder.getDescription())){
                    criteria.andEqualTo("description",returnOrder.getDescription());
            }
            // 处理备注
            if(!StringUtils.isEmpty(returnOrder.getRemark())){
                    criteria.andEqualTo("remark",returnOrder.getRemark());
            }
            // 管理员id
            if(!StringUtils.isEmpty(returnOrder.getAdminId())){
                    criteria.andEqualTo("adminId",returnOrder.getAdminId());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public int delete(Long id){
        int count = returnOrderMapper.deleteByPrimaryKey(id);
        return count;
    }

    /**
     * 修改ReturnOrder
     * @param returnOrder
     */
    @Override
    public int update(ReturnOrder returnOrder){
        int count = returnOrderMapper.updateByPrimaryKey(returnOrder);
        return count;
    }

    /**
     * 增加ReturnOrder
     * @param returnOrder
     */
    @Override
    public int add(ReturnOrder returnOrder){
        int count = returnOrderMapper.insert(returnOrder);
        return count;
    }

    /**
     * 根据ID查询ReturnOrder
     * @param id
     * @return
     */
    @Override
    public ReturnOrder findById(Long id){
        return  returnOrderMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询ReturnOrder全部数据
     * @return
     */
    @Override
    public List<ReturnOrder> findAll() {
        return returnOrderMapper.selectAll();
    }
}
