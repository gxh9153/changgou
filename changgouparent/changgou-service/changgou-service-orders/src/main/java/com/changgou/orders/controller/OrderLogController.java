package com.changgou.orders.controller;

import com.changgou.api.CommonResult;
import com.changgou.orders.pojo.OrderLog;
import com.changgou.orders.service.OrderLogService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:gxh
 * @Date 2021/1/12
 *****/
@Api(value = "OrderLogController")
@RestController
@RequestMapping("/orderLog")
@CrossOrigin
public class OrderLogController {

    @Autowired
    private OrderLogService orderLogService;

    /***
     * OrderLog分页条件搜索实现
     * @param orderLog
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "OrderLog条件分页查询",notes = "分页条件查询OrderLog方法详情",tags = {"OrderLogController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<OrderLog>> findPage(@RequestBody(required = false) @ApiParam(name = "OrderLog对象",value = "传入JSON数据",required = false) OrderLog orderLog, @PathVariable  int page, @PathVariable  int size){
        //调用OrderLogService实现分页条件查询OrderLog
        List<OrderLog> list = orderLogService.findPage(orderLog, page, size);
        return CommonResult.success(list);
    }

    /***
     * OrderLog分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "OrderLog分页查询",notes = "分页查询OrderLog方法详情",tags = {"OrderLogController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<OrderLog>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用OrderLogService实现分页查询OrderLog
        List<OrderLog> list = orderLogService.findPage(page, size);
        return CommonResult.success(list);
    }

    /***
     * 多条件搜索品牌数据
     * @param orderLog
     * @return
     */
    @ApiOperation(value = "OrderLog条件查询",notes = "条件查询OrderLog方法详情",tags = {"OrderLogController"})
    @PostMapping(value = "/search" )
    public CommonResult<List<OrderLog>> findList(@RequestBody(required = false) @ApiParam(name = "OrderLog对象",value = "传入JSON数据",required = false) OrderLog orderLog){
        //调用OrderLogService实现条件查询OrderLog
        List<OrderLog> list = orderLogService.findList(orderLog);
        return CommonResult.success(list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "OrderLog根据ID删除",notes = "根据ID删除OrderLog方法详情",tags = {"OrderLogController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping(value = "/{id}" )
    public CommonResult<Integer> delete(@PathVariable String id){
        //调用OrderLogService实现根据主键删除
        int count = orderLogService.delete(id);
        return CommonResult.success(count);
    }

    /***
     * 修改OrderLog数据
     * @param orderLog
     * @param id
     * @return
     */
    @ApiOperation(value = "OrderLog根据ID修改",notes = "根据ID修改OrderLog方法详情",tags = {"OrderLogController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @PutMapping(value="/{id}")
    public CommonResult<Integer> update(@RequestBody @ApiParam(name = "OrderLog对象",value = "传入JSON数据",required = false) OrderLog orderLog,@PathVariable String id){
        //设置主键值
        orderLog.setId(id);
        //调用OrderLogService实现修改OrderLog
        int count = orderLogService.update(orderLog);
        return CommonResult.success(count);
    }

    /***
     * 新增OrderLog数据
     * @param orderLog
     * @return
     */
    @ApiOperation(value = "OrderLog添加",notes = "添加OrderLog方法详情",tags = {"OrderLogController"})
    @PostMapping
    public CommonResult<Integer> add(@RequestBody  @ApiParam(name = "OrderLog对象",value = "传入JSON数据",required = true) OrderLog orderLog){
        //调用OrderLogService实现添加OrderLog
        int count = orderLogService.add(orderLog);
        return CommonResult.success(count);
    }

    /***
     * 根据ID查询OrderLog数据
     * @param id
     * @return
     */
    @ApiOperation(value = "OrderLog根据ID查询",notes = "根据ID查询OrderLog方法详情",tags = {"OrderLogController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @GetMapping("/{id}")
    public CommonResult<OrderLog> findById(@PathVariable String id){
        //调用OrderLogService实现根据主键查询OrderLog
        OrderLog orderLog = orderLogService.findById(id);
        return CommonResult.success(orderLog);
    }

    /***
     * 查询OrderLog全部数据
     * @return
     */
    @ApiOperation(value = "查询所有OrderLog",notes = "查询所OrderLog有方法详情",tags = {"OrderLogController"})
    @GetMapping
    public CommonResult<List<OrderLog>> findAll(){
        //调用OrderLogService实现查询所有OrderLog
        List<OrderLog> list = orderLogService.findAll();
        return CommonResult.success(list);
    }
}
