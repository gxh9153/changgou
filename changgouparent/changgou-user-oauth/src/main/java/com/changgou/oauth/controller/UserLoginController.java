package com.changgou.oauth.controller;

import com.changgou.api.CommonResult;
import com.changgou.oauth.service.UserLoginService;
import com.changgou.oauth.util.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:gxh
 * @Date: 2021/1/15 11:01
 */
@RestController
@RequestMapping("/user")
public class UserLoginController {

    @Value("${auth.clientId}")
    private String clientId;

    @Value("${auth.clientSecret}")
    private String clientSecret;

    @Autowired
    private UserLoginService userLoginService;

    @GetMapping("/login")
    public CommonResult<AuthToken> login(String username, String password) throws Exception {
        String grant_type = "password";
        AuthToken authToken = userLoginService.login(username, password, clientId, clientSecret, grant_type);
        if(authToken != null){
            return CommonResult.success(authToken,"登录成功！");
        }
        return CommonResult.failed("登录失败！");
    }
}
