package com.changgou.user.service;
import com.changgou.user.pojo.Address;

import java.util.List;

/****
 * @Author:gxh
 * @Description:Address业务层接口
 * @Date 2021/1/12
 *****/
public interface AddressService {

    /***
     * Address多条件分页查询
     * @param address
     * @param page
     * @param size
     * @return
     */
    List<Address> findPage(Address address, int page, int size);

    /***
     * Address分页查询
     * @param page
     * @param size
     * @return
     */
    List<Address> findPage(int page, int size);

    /***
     * Address多条件搜索方法
     * @param address
     * @return
     */
    List<Address> findList(Address address);

    /***
     * 删除Address
     * @param id
     */
    int delete(Integer id);

    /***
     * 修改Address数据
     * @param address
     */
    int update(Address address);

    /***
     * 新增Address
     * @param address
     */
    int add(Address address);

    /**
     * 根据ID查询Address
     * @param id
     * @return
     */
     Address findById(Integer id);

    /***
     * 查询所有Address
     * @return
     */
    List<Address> findAll();
}
