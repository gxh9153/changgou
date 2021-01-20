package com.changgou.user.service;
import com.changgou.user.pojo.User;

import java.util.List;

/****
 * @Author:gxh
 * @Description:User业务层接口
 * @Date 2021/1/12
 *****/
public interface UserService {

    /***
     * User多条件分页查询
     * @param user
     * @param page
     * @param size
     * @return
     */
    List<User> findPage(User user, int page, int size);

    /***
     * User分页查询
     * @param page
     * @param size
     * @return
     */
    List<User> findPage(int page, int size);

    /***
     * User多条件搜索方法
     * @param user
     * @return
     */
    List<User> findList(User user);

    /***
     * 删除User
     * @param id
     */
    int delete(String id);

    /***
     * 修改User数据
     * @param user
     */
    int update(User user);

    /***
     * 新增User
     * @param user
     */
    int add(User user);

    /**
     * 根据ID查询User
     * @param id
     * @return
     */
     User findById(String id);

    /***
     * 查询所有User
     * @return
     */
    List<User> findAll();
}
