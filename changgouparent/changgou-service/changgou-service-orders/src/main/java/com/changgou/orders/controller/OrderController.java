package com.changgou.orders.controller;

import com.changgou.api.CommonResult;
import com.changgou.orders.pojo.Order;
import com.changgou.orders.service.OrderService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:gxh
 * @Date 2021/1/12
 *****/
@Api(value = "OrderController")
@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    /***
     * Order分页条件搜索实现
     * @param order
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Order条件分页查询",notes = "分页条件查询Order方法详情",tags = {"OrderController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<Order>> findPage(@RequestBody(required = false) @ApiParam(name = "Order对象",value = "传入JSON数据",required = false) Order order, @PathVariable  int page, @PathVariable  int size){
        //调用OrderService实现分页条件查询Order
        List<Order> list = orderService.findPage(order, page, size);
        return CommonResult.success(list);
    }

    /***
     * Order分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Order分页查询",notes = "分页查询Order方法详情",tags = {"OrderController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<Order>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用OrderService实现分页查询Order
        List<Order> list = orderService.findPage(page, size);
        return CommonResult.success(list);
    }

    /***
     * 多条件搜索品牌数据
     * @param order
     * @return
     */
    @ApiOperation(value = "Order条件查询",notes = "条件查询Order方法详情",tags = {"OrderController"})
    @PostMapping(value = "/search" )
    public CommonResult<List<Order>> findList(@RequestBody(required = false) @ApiParam(name = "Order对象",value = "传入JSON数据",required = false) Order order){
        //调用OrderService实现条件查询Order
        List<Order> list = orderService.findList(order);
        return CommonResult.success(list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Order根据ID删除",notes = "根据ID删除Order方法详情",tags = {"OrderController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping(value = "/{id}" )
    public CommonResult<Integer> delete(@PathVariable String id){
        //调用OrderService实现根据主键删除
        int count = orderService.delete(id);
        return CommonResult.success(count);
    }

    /***
     * 修改Order数据
     * @param order
     * @param id
     * @return
     */
    @ApiOperation(value = "Order根据ID修改",notes = "根据ID修改Order方法详情",tags = {"OrderController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @PutMapping(value="/{id}")
    public CommonResult<Integer> update(@RequestBody @ApiParam(name = "Order对象",value = "传入JSON数据",required = false) Order order,@PathVariable String id){
        //设置主键值
        order.setId(id);
        //调用OrderService实现修改Order
        int count = orderService.update(order);
        return CommonResult.success(count);
    }

    /***
     * 新增Order数据
     * @param order
     * @return
     */
    @ApiOperation(value = "Order添加",notes = "添加Order方法详情",tags = {"OrderController"})
    @PostMapping
    public CommonResult<Integer> add(@RequestBody  @ApiParam(name = "Order对象",value = "传入JSON数据",required = true) Order order){
        //调用OrderService实现添加Order
        int count = orderService.add(order);
        return CommonResult.success(count);
    }

    /***
     * 根据ID查询Order数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Order根据ID查询",notes = "根据ID查询Order方法详情",tags = {"OrderController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @GetMapping("/{id}")
    public CommonResult<Order> findById(@PathVariable String id){
        //调用OrderService实现根据主键查询Order
        Order order = orderService.findById(id);
        return CommonResult.success(order);
    }

    /***
     * 查询Order全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Order",notes = "查询所Order有方法详情",tags = {"OrderController"})
    @GetMapping
    public CommonResult<List<Order>> findAll(){
        //调用OrderService实现查询所有Order
        List<Order> list = orderService.findAll();
        return CommonResult.success(list);
    }
}
