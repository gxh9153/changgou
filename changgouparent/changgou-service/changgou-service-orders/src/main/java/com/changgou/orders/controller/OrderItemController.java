package com.changgou.orders.controller;

import com.changgou.api.CommonResult;
import com.changgou.orders.pojo.OrderItem;
import com.changgou.orders.service.OrderItemService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:gxh
 * @Date 2021/1/12
 *****/
@Api(value = "OrderItemController")
@RestController
@RequestMapping("/orderItem")
@CrossOrigin
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    /***
     * OrderItem分页条件搜索实现
     * @param orderItem
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "OrderItem条件分页查询",notes = "分页条件查询OrderItem方法详情",tags = {"OrderItemController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<OrderItem>> findPage(@RequestBody(required = false) @ApiParam(name = "OrderItem对象",value = "传入JSON数据",required = false) OrderItem orderItem, @PathVariable  int page, @PathVariable  int size){
        //调用OrderItemService实现分页条件查询OrderItem
        List<OrderItem> list = orderItemService.findPage(orderItem, page, size);
        return CommonResult.success(list);
    }

    /***
     * OrderItem分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "OrderItem分页查询",notes = "分页查询OrderItem方法详情",tags = {"OrderItemController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<OrderItem>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用OrderItemService实现分页查询OrderItem
        List<OrderItem> list = orderItemService.findPage(page, size);
        return CommonResult.success(list);
    }

    /***
     * 多条件搜索品牌数据
     * @param orderItem
     * @return
     */
    @ApiOperation(value = "OrderItem条件查询",notes = "条件查询OrderItem方法详情",tags = {"OrderItemController"})
    @PostMapping(value = "/search" )
    public CommonResult<List<OrderItem>> findList(@RequestBody(required = false) @ApiParam(name = "OrderItem对象",value = "传入JSON数据",required = false) OrderItem orderItem){
        //调用OrderItemService实现条件查询OrderItem
        List<OrderItem> list = orderItemService.findList(orderItem);
        return CommonResult.success(list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "OrderItem根据ID删除",notes = "根据ID删除OrderItem方法详情",tags = {"OrderItemController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping(value = "/{id}" )
    public CommonResult<Integer> delete(@PathVariable String id){
        //调用OrderItemService实现根据主键删除
        int count = orderItemService.delete(id);
        return CommonResult.success(count);
    }

    /***
     * 修改OrderItem数据
     * @param orderItem
     * @param id
     * @return
     */
    @ApiOperation(value = "OrderItem根据ID修改",notes = "根据ID修改OrderItem方法详情",tags = {"OrderItemController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @PutMapping(value="/{id}")
    public CommonResult<Integer> update(@RequestBody @ApiParam(name = "OrderItem对象",value = "传入JSON数据",required = false) OrderItem orderItem,@PathVariable String id){
        //设置主键值
        orderItem.setId(id);
        //调用OrderItemService实现修改OrderItem
        int count = orderItemService.update(orderItem);
        return CommonResult.success(count);
    }

    /***
     * 新增OrderItem数据
     * @param orderItem
     * @return
     */
    @ApiOperation(value = "OrderItem添加",notes = "添加OrderItem方法详情",tags = {"OrderItemController"})
    @PostMapping
    public CommonResult<Integer> add(@RequestBody  @ApiParam(name = "OrderItem对象",value = "传入JSON数据",required = true) OrderItem orderItem){
        //调用OrderItemService实现添加OrderItem
        int count = orderItemService.add(orderItem);
        return CommonResult.success(count);
    }

    /***
     * 根据ID查询OrderItem数据
     * @param id
     * @return
     */
    @ApiOperation(value = "OrderItem根据ID查询",notes = "根据ID查询OrderItem方法详情",tags = {"OrderItemController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @GetMapping("/{id}")
    public CommonResult<OrderItem> findById(@PathVariable String id){
        //调用OrderItemService实现根据主键查询OrderItem
        OrderItem orderItem = orderItemService.findById(id);
        return CommonResult.success(orderItem);
    }

    /***
     * 查询OrderItem全部数据
     * @return
     */
    @ApiOperation(value = "查询所有OrderItem",notes = "查询所OrderItem有方法详情",tags = {"OrderItemController"})
    @GetMapping
    public CommonResult<List<OrderItem>> findAll(){
        //调用OrderItemService实现查询所有OrderItem
        List<OrderItem> list = orderItemService.findAll();
        return CommonResult.success(list);
    }
}
