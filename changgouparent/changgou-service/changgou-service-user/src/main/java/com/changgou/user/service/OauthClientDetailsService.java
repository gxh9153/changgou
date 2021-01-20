package com.changgou.user.service;
import com.changgou.user.pojo.OauthClientDetails;

import java.util.List;

/****
 * @Author:gxh
 * @Description:User业务层接口
 * @Date 2021/1/12
 *****/
public interface OauthClientDetailsService {

    /***
     * OauthClientDetails多条件分页查询
     * @param oauthClientDetails
     * @param page
     * @param size
     * @return
     */
    List<OauthClientDetails> findPage(OauthClientDetails oauthClientDetails, int page, int size);

    /***
     * OauthClientDetails分页查询
     * @param page
     * @param size
     * @return
     */
    List<OauthClientDetails> findPage(int page, int size);

    /***
     * OauthClientDetails多条件搜索方法
     * @param oauthClientDetails
     * @return
     */
    List<OauthClientDetails> findList(OauthClientDetails oauthClientDetails);

    /***
     * 删除OauthClientDetails
     * @param id
     */
    int delete(String id);

    /***
     * 修改OauthClientDetails数据
     * @param oauthClientDetails
     */
    int update(OauthClientDetails oauthClientDetails);

    /***
     * 新增OauthClientDetails
     * @param oauthClientDetails
     */
    int add(OauthClientDetails oauthClientDetails);

    /**
     * 根据ID查询OauthClientDetails
     * @param id
     * @return
     */
     OauthClientDetails findById(String id);

    /***
     * 查询所有OauthClientDetails
     * @return
     */
    List<OauthClientDetails> findAll();
}
