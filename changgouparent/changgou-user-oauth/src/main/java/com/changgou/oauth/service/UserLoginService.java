package com.changgou.oauth.service;

import com.changgou.oauth.util.AuthToken;

/**
 * @Author:gxh
 * @Date: 2021/1/14 11:40
 */
public interface UserLoginService {

    /**
     * 用户登录认证
     * @param username
     * @param password
     * @param clientId
     * @param clientSecret
     */
    AuthToken login(String username, String password, String clientId, String clientSecret, String grant_type)throws Exception;
}
