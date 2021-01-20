package com.gxh;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:gxh
 * @Date: 2021/1/12 17:26
 */
public class JwtTest {

    @Test
    public void createToken(){
        Map<String,Object> userInfo = new HashMap<String,Object>();
        userInfo.put("address","江西上饶");
        userInfo.put("userName","gxh");
        userInfo.put("money",5000);
        JwtBuilder builder = Jwts.builder()
                .setId("gxh")//设置唯一编号
                .setSubject("gxh")//设置主体，可以是Json数据
                .setIssuedAt(new Date())//设置签发时间
                .setClaims(userInfo)//自定义载荷信息
                .setExpiration(new Date(System.currentTimeMillis()+30000))//设置过期时间
                .signWith(SignatureAlgorithm.HS256,"gongxihui");
        //构建并返回一个字符串
        System.out.println(builder.compact());
    }
    @Test
    public void parseToken(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJhZGRyZXNzIjoi5rGf6KW_5LiK6aW2IiwibW9uZXkiOjUwMDAsInVzZXJOYW1lIjoiZ3hoIiwiZXhwIjoxNjEwNDQ2NjY1fQ.lkmaiWEzgl86NSGGAZkpyXjtsuGtoZPgComFA_L95Pc";
        Claims claims = Jwts.parser().setSigningKey("gongxihui").parseClaimsJws(token).getBody();
        System.out.println(claims.toString());
    }
}
