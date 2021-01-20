package com.changgou.orders.controller;

import com.changgou.api.CommonResult;
import com.changgou.entity.TokenDecode;
import com.changgou.orders.pojo.OrderItem;
import com.changgou.orders.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author:gxh
 * @Date: 2021/1/18 14:26
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/add")
    public CommonResult add(Long id,Integer num){
        //String username = "szitheima";
        String username = getUsername();
        cartService.add(id,num,username);
        return CommonResult.success("添加购物车成功！");
    }

    @GetMapping("/list")
    public CommonResult<List<OrderItem>> list(){
        String username = getUsername();
        List<OrderItem> list = cartService.list(username);
        return CommonResult.success(list,"查询购物车成功");
    }

    /**
     * 获取用户名
     * @return
     */
    private String getUsername() {
        Map<String, String> userInfo = TokenDecode.getUserInfo();
        return userInfo.get("username");
    }
}
