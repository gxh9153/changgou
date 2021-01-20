package com.changgou.user.feign;

import com.changgou.api.CommonResult;
import com.changgou.user.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/****
 * @Author:gxh
 * @Date 2021/1/15
 *****/
@FeignClient(value = "user")
@RequestMapping("/user")
public interface UserFeign {
    @GetMapping("/load/{id}")
    public CommonResult<User> findByName(@PathVariable(name = "id") String id);


    /**
     * 添加积分
     * @param points
     * @param username
     * @return
     */
    @GetMapping(value = "/points/add")
    public CommonResult addPoints(@RequestParam(value = "points") Integer points
            , @RequestParam(value = "username") String username);

}
