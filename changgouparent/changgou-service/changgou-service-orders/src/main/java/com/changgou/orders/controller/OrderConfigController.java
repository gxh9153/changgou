package com.changgou.orders.controller;

import com.changgou.api.CommonResult;
import com.changgou.orders.pojo.OrderConfig;
import com.changgou.orders.service.OrderConfigService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:gxh
 * @Date 2021/1/12
 *****/
@Api(value = "OrderConfigController")
@RestController
@RequestMapping("/orderConfig")
@CrossOrigin
public class OrderConfigController {

    @Autowired
    private OrderConfigService orderConfigService;

    /***
     * OrderConfig分页条件搜索实现
     * @param orderConfig
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "OrderConfig条件分页查询",notes = "分页条件查询OrderConfig方法详情",tags = {"OrderConfigController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<OrderConfig>> findPage(@RequestBody(required = false) @ApiParam(name = "OrderConfig对象",value = "传入JSON数据",required = false) OrderConfig orderConfig, @PathVariable  int page, @PathVariable  int size){
        //调用OrderConfigService实现分页条件查询OrderConfig
        List<OrderConfig> list = orderConfigService.findPage(orderConfig, page, size);
        return CommonResult.success(list);
    }

    /***
     * OrderConfig分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "OrderConfig分页查询",notes = "分页查询OrderConfig方法详情",tags = {"OrderConfigController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<OrderConfig>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用OrderConfigService实现分页查询OrderConfig
        List<OrderConfig> list = orderConfigService.findPage(page, size);
        return CommonResult.success(list);
    }

    /***
     * 多条件搜索品牌数据
     * @param orderConfig
     * @return
     */
    @ApiOperation(value = "OrderConfig条件查询",notes = "条件查询OrderConfig方法详情",tags = {"OrderConfigController"})
    @PostMapping(value = "/search" )
    public CommonResult<List<OrderConfig>> findList(@RequestBody(required = false) @ApiParam(name = "OrderConfig对象",value = "传入JSON数据",required = false) OrderConfig orderConfig){
        //调用OrderConfigService实现条件查询OrderConfig
        List<OrderConfig> list = orderConfigService.findList(orderConfig);
        return CommonResult.success(list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "OrderConfig根据ID删除",notes = "根据ID删除OrderConfig方法详情",tags = {"OrderConfigController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public CommonResult<Integer> delete(@PathVariable Integer id){
        //调用OrderConfigService实现根据主键删除
        int count = orderConfigService.delete(id);
        return CommonResult.success(count);
    }

    /***
     * 修改OrderConfig数据
     * @param orderConfig
     * @param id
     * @return
     */
    @ApiOperation(value = "OrderConfig根据ID修改",notes = "根据ID修改OrderConfig方法详情",tags = {"OrderConfigController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public CommonResult<Integer> update(@RequestBody @ApiParam(name = "OrderConfig对象",value = "传入JSON数据",required = false) OrderConfig orderConfig,@PathVariable Integer id){
        //设置主键值
        orderConfig.setId(id);
        //调用OrderConfigService实现修改OrderConfig
        int count = orderConfigService.update(orderConfig);
        return CommonResult.success(count);
    }

    /***
     * 新增OrderConfig数据
     * @param orderConfig
     * @return
     */
    @ApiOperation(value = "OrderConfig添加",notes = "添加OrderConfig方法详情",tags = {"OrderConfigController"})
    @PostMapping
    public CommonResult<Integer> add(@RequestBody  @ApiParam(name = "OrderConfig对象",value = "传入JSON数据",required = true) OrderConfig orderConfig){
        //调用OrderConfigService实现添加OrderConfig
        int count = orderConfigService.add(orderConfig);
        return CommonResult.success(count);
    }

    /***
     * 根据ID查询OrderConfig数据
     * @param id
     * @return
     */
    @ApiOperation(value = "OrderConfig根据ID查询",notes = "根据ID查询OrderConfig方法详情",tags = {"OrderConfigController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public CommonResult<OrderConfig> findById(@PathVariable Integer id){
        //调用OrderConfigService实现根据主键查询OrderConfig
        OrderConfig orderConfig = orderConfigService.findById(id);
        return CommonResult.success(orderConfig);
    }

    /***
     * 查询OrderConfig全部数据
     * @return
     */
    @ApiOperation(value = "查询所有OrderConfig",notes = "查询所OrderConfig有方法详情",tags = {"OrderConfigController"})
    @GetMapping
    public CommonResult<List<OrderConfig>> findAll(){
        //调用OrderConfigService实现查询所有OrderConfig
        List<OrderConfig> list = orderConfigService.findAll();
        return CommonResult.success(list);
    }
}
