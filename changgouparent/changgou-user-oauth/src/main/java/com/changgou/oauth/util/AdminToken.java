package com.changgou.oauth.util;

import com.alibaba.fastjson.JSON;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:gxh
 * @Date: 2021/1/19 11:51
 */
public class AdminToken {

    public static String getAdminToken(){
        //证书文件路径
        String key_location="gxhlisa.jks";
        //秘钥库密码
        String key_password="gxhlisa";
        //秘钥密码
        String keypwd = "gxhlisa";
        //秘钥别名
        String alias = "gxhlisa";

        //访问证书路径 读取jks的文件
        ClassPathResource resource = new ClassPathResource(key_location);

        //创建秘钥工厂
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(resource,key_password.toCharArray());

        //读取秘钥对(公钥、私钥)
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alias,keypwd.toCharArray());

        //获取私钥
        RSAPrivateKey rsaPrivate = (RSAPrivateKey) keyPair.getPrivate();

        //自定义Payload
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("id", "1");
        tokenMap.put("name", "gxh");
        tokenMap.put("authorities",new String[]{"admin","oauth"});

        //生成Jwt令牌
        Jwt jwt = JwtHelper.encode(JSON.toJSONString(tokenMap), new RsaSigner(rsaPrivate));


        //取出令牌
        String adminToken = jwt.getEncoded();
        return adminToken;
    }
}
