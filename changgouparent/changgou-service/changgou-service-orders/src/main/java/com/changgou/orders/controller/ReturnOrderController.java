package com.changgou.orders.controller;

import com.changgou.api.CommonResult;
import com.changgou.orders.pojo.ReturnOrder;
import com.changgou.orders.service.ReturnOrderService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:gxh
 * @Date 2021/1/12
 *****/
@Api(value = "ReturnOrderController")
@RestController
@RequestMapping("/returnOrder")
@CrossOrigin
public class ReturnOrderController {

    @Autowired
    private ReturnOrderService returnOrderService;

    /***
     * ReturnOrder分页条件搜索实现
     * @param returnOrder
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "ReturnOrder条件分页查询",notes = "分页条件查询ReturnOrder方法详情",tags = {"ReturnOrderController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<ReturnOrder>> findPage(@RequestBody(required = false) @ApiParam(name = "ReturnOrder对象",value = "传入JSON数据",required = false) ReturnOrder returnOrder, @PathVariable  int page, @PathVariable  int size){
        //调用ReturnOrderService实现分页条件查询ReturnOrder
        List<ReturnOrder> list = returnOrderService.findPage(returnOrder, page, size);
        return CommonResult.success(list);
    }

    /***
     * ReturnOrder分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "ReturnOrder分页查询",notes = "分页查询ReturnOrder方法详情",tags = {"ReturnOrderController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<ReturnOrder>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用ReturnOrderService实现分页查询ReturnOrder
        List<ReturnOrder> list = returnOrderService.findPage(page, size);
        return CommonResult.success(list);
    }

    /***
     * 多条件搜索品牌数据
     * @param returnOrder
     * @return
     */
    @ApiOperation(value = "ReturnOrder条件查询",notes = "条件查询ReturnOrder方法详情",tags = {"ReturnOrderController"})
    @PostMapping(value = "/search" )
    public CommonResult<List<ReturnOrder>> findList(@RequestBody(required = false) @ApiParam(name = "ReturnOrder对象",value = "传入JSON数据",required = false) ReturnOrder returnOrder){
        //调用ReturnOrderService实现条件查询ReturnOrder
        List<ReturnOrder> list = returnOrderService.findList(returnOrder);
        return CommonResult.success(list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "ReturnOrder根据ID删除",notes = "根据ID删除ReturnOrder方法详情",tags = {"ReturnOrderController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @DeleteMapping(value = "/{id}" )
    public CommonResult<Integer> delete(@PathVariable Long id){
        //调用ReturnOrderService实现根据主键删除
        int count = returnOrderService.delete(id);
        return CommonResult.success(count);
    }

    /***
     * 修改ReturnOrder数据
     * @param returnOrder
     * @param id
     * @return
     */
    @ApiOperation(value = "ReturnOrder根据ID修改",notes = "根据ID修改ReturnOrder方法详情",tags = {"ReturnOrderController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @PutMapping(value="/{id}")
    public CommonResult<Integer> update(@RequestBody @ApiParam(name = "ReturnOrder对象",value = "传入JSON数据",required = false) ReturnOrder returnOrder,@PathVariable Long id){
        //设置主键值
        returnOrder.setId(id);
        //调用ReturnOrderService实现修改ReturnOrder
        int count = returnOrderService.update(returnOrder);
        return CommonResult.success(count);
    }

    /***
     * 新增ReturnOrder数据
     * @param returnOrder
     * @return
     */
    @ApiOperation(value = "ReturnOrder添加",notes = "添加ReturnOrder方法详情",tags = {"ReturnOrderController"})
    @PostMapping
    public CommonResult<Integer> add(@RequestBody  @ApiParam(name = "ReturnOrder对象",value = "传入JSON数据",required = true) ReturnOrder returnOrder){
        //调用ReturnOrderService实现添加ReturnOrder
        int count = returnOrderService.add(returnOrder);
        return CommonResult.success(count);
    }

    /***
     * 根据ID查询ReturnOrder数据
     * @param id
     * @return
     */
    @ApiOperation(value = "ReturnOrder根据ID查询",notes = "根据ID查询ReturnOrder方法详情",tags = {"ReturnOrderController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public CommonResult<ReturnOrder> findById(@PathVariable Long id){
        //调用ReturnOrderService实现根据主键查询ReturnOrder
        ReturnOrder returnOrder = returnOrderService.findById(id);
        return CommonResult.success(returnOrder);
    }

    /***
     * 查询ReturnOrder全部数据
     * @return
     */
    @ApiOperation(value = "查询所有ReturnOrder",notes = "查询所ReturnOrder有方法详情",tags = {"ReturnOrderController"})
    @GetMapping
    public CommonResult<List<ReturnOrder>> findAll(){
        //调用ReturnOrderService实现查询所有ReturnOrder
        List<ReturnOrder> list = returnOrderService.findAll();
        return CommonResult.success(list);
    }
}
