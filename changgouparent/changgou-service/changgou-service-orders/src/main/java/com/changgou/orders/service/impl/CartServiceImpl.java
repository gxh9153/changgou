package com.changgou.orders.service.impl;

import com.changgou.api.CommonResult;
import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.feign.SpuFeign;
import com.changgou.goods.pojo.Sku;
import com.changgou.goods.pojo.Spu;
import com.changgou.orders.pojo.OrderItem;
import com.changgou.orders.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:gxh
 * @Date: 2021/1/18 14:12
 */

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private SpuFeign spuFeign;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加购物车
     * @param id
     * @param num
     * @param username
     */
    @Override
    public void add(Long id, Integer num, String username) {
        if(num<=0){
            //删除掉原来的商品
            redisTemplate.boundHashOps(username+"_Cart").delete(id);
            Long size = redisTemplate.boundHashOps(username + "_Cart").size();
            if(size == null || size<=0){
                //删除命名空间
                redisTemplate.delete(username+"_Cart");
            }
            return;
        }

        //查询sku数据
        CommonResult<Sku> skuResult = skuFeign.findById(id);

        Sku sku = skuResult.getData();

        //查询spu数据
        CommonResult<Spu> spuResult = spuFeign.findById(sku.getSpuId());
        Spu spu = spuResult.getData();

        //将数据存入orderItem对象中
        OrderItem orderItem = new OrderItem();
        orderItem.setCategoryId1(spu.getCategory1Id());
        orderItem.setCategoryId2(spu.getCategory2Id());
        orderItem.setCategoryId3(spu.getCategory3Id());
        orderItem.setSpuId(spu.getId());
        orderItem.setSkuId(id);
        orderItem.setName(sku.getName());//商品的名称  sku的名称
        orderItem.setPrice(sku.getPrice());//sku的单价
        orderItem.setNum(num);//购买的数量
        orderItem.setMoney(orderItem.getNum() * orderItem.getPrice());//单价* 数量
        orderItem.setPayMoney(orderItem.getNum() * orderItem.getPrice());//单价* 数量
        orderItem.setImage(sku.getImage());//商品的图片地址

        //将数据存入redis中
        redisTemplate.boundHashOps(username+"_Cart").put(id,orderItem);
    }

    /**
     * 获取购物车数据
     * @param username
     * @return
     */
    @Override
    public List<OrderItem> list(String username) {
        List<OrderItem>list = redisTemplate.boundHashOps(username + "_Cart").values();
        return list;
    }
}
