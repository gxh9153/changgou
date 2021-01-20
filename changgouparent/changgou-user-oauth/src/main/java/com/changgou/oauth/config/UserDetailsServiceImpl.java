package com.changgou.oauth.config;

import com.changgou.api.CommonResult;
import com.changgou.oauth.util.UserJwt;
import com.changgou.user.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/*****
 * 自定义授权认证类
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ClientDetailsService clientDetailsService;

    @Autowired
    UserFeign userFeign;

    /****
     * 自定义授权认证
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /**客户端信息认证开始*/

        //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
        if(authentication==null){
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
            if(clientDetails!=null){
                //秘钥
                String clientSecret = clientDetails.getClientSecret();
                //静态方式
                //return new User(username,new BCryptPasswordEncoder().encode(clientSecret), AuthorityUtils.commaSeparatedStringToAuthorityList(""));
                //数据库查找方式
                return new User(
                        username,//客户端id
                        clientSecret, //客户端秘钥
                        AuthorityUtils.commaSeparatedStringToAuthorityList(""));//权限
            }
        }
        /**客户端信息认证结束*/
        /**用户信息认证开始*/
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        /**
         * clientId :客户端id
         * clientSecret 客户端秘钥
         * 普通用户-》账号 任意账号，密码gxhlisa
         */

        //根据用户名查询用户信息
        //String pwd = new BCryptPasswordEncoder().encode("gxhlisa");
       //System.out.println( new BCryptPasswordEncoder().encode("gxhlisa"));

        /**
         * 从数据库查询查询用户信息
         * 1：没有令牌，Feign调用之前生成令牌（admin）
         * 2：令牌需要携带过去
         * 3：令牌需要存放到Header文件中
         * 4：请求-》Feign调用-》调用拦截器RequestInterceptor->Feign调用之前
         */
        CommonResult<com.changgou.user.pojo.User> userResult = userFeign.findByName(username);
        if(userResult == null || userResult.getData() == null){
            return null;
        }
        String pwd = userResult.getData().getPassword();
        //创建User对象
        String permissions = "user,vip,admin";//指定用户的角色信息  查询数据库
        UserJwt userDetails = new UserJwt(username,pwd,AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
        return userDetails;
        /**用户信息认证结束*/
    }
}
