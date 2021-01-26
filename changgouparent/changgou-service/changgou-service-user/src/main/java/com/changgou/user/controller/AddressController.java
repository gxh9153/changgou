package com.changgou.user.controller;

import com.changgou.api.CommonResult;
import com.changgou.entity.TokenDecode;
import com.changgou.user.pojo.Address;
import com.changgou.user.service.AddressService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/****
 * @Author:gxh
 * @Date 2021/1/12
 *****/
@Api(value = "AddressController")
@RestController
@RequestMapping("/address")
@CrossOrigin
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * 根据用户名查询收货地址列表
     * @return
     */
    @GetMapping("/list")
    public CommonResult<List<Address>> list(){
        Map<String, String> userInfo = TokenDecode.getUserInfo();
        String username = userInfo.get("username");
        List<Address> list = addressService.list(username);
        return CommonResult.success(list,"收货地址列表查询成功！");
    }

    /***
     * Address分页条件搜索实现
     * @param address
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Address条件分页查询",notes = "分页条件查询Address方法详情",tags = {"AddressController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<Address>> findPage(@RequestBody(required = false) @ApiParam(name = "Address对象",value = "传入JSON数据",required = false) Address address, @PathVariable  int page, @PathVariable  int size){
        //调用AddressService实现分页条件查询Address
        List<Address> list = addressService.findPage(address, page, size);
        return CommonResult.success(list);
    }

    /***
     * Address分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Address分页查询",notes = "分页查询Address方法详情",tags = {"AddressController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<Address>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用AddressService实现分页查询Address
        List<Address> list = addressService.findPage(page, size);
        return CommonResult.success(list);
    }

    /***
     * 多条件搜索品牌数据
     * @param address
     * @return
     */
    @ApiOperation(value = "Address条件查询",notes = "条件查询Address方法详情",tags = {"AddressController"})
    @PostMapping(value = "/search" )
    public CommonResult<List<Address>> findList(@RequestBody(required = false) @ApiParam(name = "Address对象",value = "传入JSON数据",required = false) Address address){
        //调用AddressService实现条件查询Address
        List<Address> list = addressService.findList(address);
        return CommonResult.success(list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Address根据ID删除",notes = "根据ID删除Address方法详情",tags = {"AddressController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public CommonResult<Integer> delete(@PathVariable Integer id){
        //调用AddressService实现根据主键删除
        int count = addressService.delete(id);
        return CommonResult.success(count);
    }

    /***
     * 修改Address数据
     * @param address
     * @param id
     * @return
     */
    @ApiOperation(value = "Address根据ID修改",notes = "根据ID修改Address方法详情",tags = {"AddressController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public CommonResult<Integer> update(@RequestBody @ApiParam(name = "Address对象",value = "传入JSON数据",required = false) Address address,@PathVariable Integer id){
        //设置主键值
        address.setId(id);
        //调用AddressService实现修改Address
        int count = addressService.update(address);
        return CommonResult.success(count);
    }

    /***
     * 新增Address数据
     * @param address
     * @return
     */
    @ApiOperation(value = "Address添加",notes = "添加Address方法详情",tags = {"AddressController"})
    @PostMapping
    public CommonResult<Integer> add(@RequestBody  @ApiParam(name = "Address对象",value = "传入JSON数据",required = true) Address address){
        //调用AddressService实现添加Address
        int count = addressService.add(address);
        return CommonResult.success(count);
    }

    /***
     * 根据ID查询Address数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Address根据ID查询",notes = "根据ID查询Address方法详情",tags = {"AddressController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public CommonResult<Address> findById(@PathVariable Integer id){
        //调用AddressService实现根据主键查询Address
        Address address = addressService.findById(id);
        return CommonResult.success(address);
    }

    /***
     * 查询Address全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Address",notes = "查询所Address有方法详情",tags = {"AddressController"})
    @GetMapping
    public CommonResult<List<Address>> findAll(){
        //调用AddressService实现查询所有Address
        List<Address> list = addressService.findAll();
        return CommonResult.success(list);
    }
}
