package com.changgou.entity;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import springfox.documentation.spring.web.json.Json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 令牌解析工具类
 * @Author:gxh
 * @Date: 2021/1/19 13:30
 */
public class TokenDecode {

    //公钥
    private static final String PUBLIC_KEY="public.key";

    private static String publicKey = "";

    public static String getPublicKey(){
        if(StringUtils.isNotEmpty(publicKey)){
            return publicKey;
        }
        ClassPathResource resource = new ClassPathResource(PUBLIC_KEY);
        try {
            InputStreamReader inputStreamResource = new InputStreamReader(resource.getInputStream());
            BufferedReader br = new BufferedReader(inputStreamResource);
            publicKey = br.lines().collect(Collectors.joining("\n"));
            return publicKey;
        } catch (IOException e) {
            return null;
        }

    }

    /**
     * 解析令牌数据
     * @param token
     * @return
     */
    public static Map<String,String> decodeToken(String token){
        //校验jwt
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(getPublicKey()));

        //获取jwt原始内容
        String claims = jwt.getClaims();
        return JSON.parseObject(claims,Map.class);
    }

    public static  Map<String,String> getUserInfo(){
        //获取授权信息  获取封装的用户所有信息    用户令牌信息-》解析令牌信息-》username
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        String token = details.getTokenValue();//获取用户令牌信息
        return decodeToken(token);
    }

}
