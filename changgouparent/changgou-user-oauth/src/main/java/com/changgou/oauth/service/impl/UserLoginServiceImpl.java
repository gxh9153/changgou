package com.changgou.oauth.service.impl;

import com.changgou.oauth.service.UserLoginService;
import com.changgou.oauth.util.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Map;

/**
 * @Author:gxh
 * @Date: 2021/1/14 11:40
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

    /**
     * 用户登录认证
     * @param username
     * @param password
     * @param clientId
     * @param clientSecret
     * @param grant_type
     *
     * 请求参数  username password grantType
     * 请求头传递  Basic Base64(clientId,clientSecret)  Authorization = Basic **********
     */

    //调用http请求的工具类
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    private final String client = "user-auth";


    @Override
    public AuthToken login(String username, String password, String clientId, String clientSecret, String grant_type)throws  Exception {

        ServiceInstance serviceInstance = loadBalancerClient.choose(client);

        //请求地址
        String url = getUrl(serviceInstance);

        //请求参数的封装
        HttpEntity<MultiValueMap> httpEntity = getParameters(username, password, clientId, clientSecret, grant_type);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Map.class);

        //System.out.println(response.getBody());

        //返回值的封装
        AuthToken authToken = getAuthToken(response);
        return authToken;
    }

    private AuthToken getAuthToken(ResponseEntity<Map> response) {
        AuthToken authToken = new AuthToken();
        Map<String,String> body = response.getBody();
        authToken.setAccessToken(body.get("access_token"));
        authToken.setRefreshToken(body.get("refresh_token"));
        authToken.setJti(body.get("jti"));
        return authToken;
    }

    private HttpEntity<MultiValueMap> getParameters(String username, String password, String clientId, String clientSecret, String grant_type) throws UnsupportedEncodingException {
        //请求参数的封装
        MultiValueMap<String,String> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("username",username);
        paramMap.add("password",password);
        paramMap.add("grant_type",grant_type);

        //请求头的封装
        String authorization = "Basic "+new String(Base64.getEncoder().encode((clientId+":"+clientSecret).getBytes()),"UTF-8");
        MultiValueMap<String,String> headMap = new LinkedMultiValueMap();
        headMap.add("Authorization",authorization);

        //HttpEntity
        return new HttpEntity<MultiValueMap>(paramMap,headMap);
    }

    //获取请求地址
    private String getUrl(ServiceInstance serviceInstance) {
        return serviceInstance.getUri()+"/oauth/token";
    }

    public static void main(String[] args) {
        byte[] decode = Base64.getDecoder().decode(new String("Z3hobGlzYTpneGhsaXNh").getBytes());
        System.out.println(new String(decode));
    }
}

