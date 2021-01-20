package com.changgou.orders.controller;

import com.changgou.api.CommonResult;
import com.changgou.orders.pojo.ReturnCause;
import com.changgou.orders.service.ReturnCauseService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:gxh
 * @Date 2021/1/12
 *****/
@Api(value = "ReturnCauseController")
@RestController
@RequestMapping("/returnCause")
@CrossOrigin
public class ReturnCauseController {

    @Autowired
    private ReturnCauseService returnCauseService;

    /***
     * ReturnCause分页条件搜索实现
     * @param returnCause
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "ReturnCause条件分页查询",notes = "分页条件查询ReturnCause方法详情",tags = {"ReturnCauseController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<ReturnCause>> findPage(@RequestBody(required = false) @ApiParam(name = "ReturnCause对象",value = "传入JSON数据",required = false) ReturnCause returnCause, @PathVariable  int page, @PathVariable  int size){
        //调用ReturnCauseService实现分页条件查询ReturnCause
        List<ReturnCause> list = returnCauseService.findPage(returnCause, page, size);
        return CommonResult.success(list);
    }

    /***
     * ReturnCause分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "ReturnCause分页查询",notes = "分页查询ReturnCause方法详情",tags = {"ReturnCauseController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<ReturnCause>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用ReturnCauseService实现分页查询ReturnCause
        List<ReturnCause> list = returnCauseService.findPage(page, size);
        return CommonResult.success(list);
    }

    /***
     * 多条件搜索品牌数据
     * @param returnCause
     * @return
     */
    @ApiOperation(value = "ReturnCause条件查询",notes = "条件查询ReturnCause方法详情",tags = {"ReturnCauseController"})
    @PostMapping(value = "/search" )
    public CommonResult<List<ReturnCause>> findList(@RequestBody(required = false) @ApiParam(name = "ReturnCause对象",value = "传入JSON数据",required = false) ReturnCause returnCause){
        //调用ReturnCauseService实现条件查询ReturnCause
        List<ReturnCause> list = returnCauseService.findList(returnCause);
        return CommonResult.success(list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "ReturnCause根据ID删除",notes = "根据ID删除ReturnCause方法详情",tags = {"ReturnCauseController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public CommonResult<Integer> delete(@PathVariable Integer id){
        //调用ReturnCauseService实现根据主键删除
        int count = returnCauseService.delete(id);
        return CommonResult.success(count);
    }

    /***
     * 修改ReturnCause数据
     * @param returnCause
     * @param id
     * @return
     */
    @ApiOperation(value = "ReturnCause根据ID修改",notes = "根据ID修改ReturnCause方法详情",tags = {"ReturnCauseController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public CommonResult<Integer> update(@RequestBody @ApiParam(name = "ReturnCause对象",value = "传入JSON数据",required = false) ReturnCause returnCause,@PathVariable Integer id){
        //设置主键值
        returnCause.setId(id);
        //调用ReturnCauseService实现修改ReturnCause
        int count = returnCauseService.update(returnCause);
        return CommonResult.success(count);
    }

    /***
     * 新增ReturnCause数据
     * @param returnCause
     * @return
     */
    @ApiOperation(value = "ReturnCause添加",notes = "添加ReturnCause方法详情",tags = {"ReturnCauseController"})
    @PostMapping
    public CommonResult<Integer> add(@RequestBody  @ApiParam(name = "ReturnCause对象",value = "传入JSON数据",required = true) ReturnCause returnCause){
        //调用ReturnCauseService实现添加ReturnCause
        int count = returnCauseService.add(returnCause);
        return CommonResult.success(count);
    }

    /***
     * 根据ID查询ReturnCause数据
     * @param id
     * @return
     */
    @ApiOperation(value = "ReturnCause根据ID查询",notes = "根据ID查询ReturnCause方法详情",tags = {"ReturnCauseController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public CommonResult<ReturnCause> findById(@PathVariable Integer id){
        //调用ReturnCauseService实现根据主键查询ReturnCause
        ReturnCause returnCause = returnCauseService.findById(id);
        return CommonResult.success(returnCause);
    }

    /***
     * 查询ReturnCause全部数据
     * @return
     */
    @ApiOperation(value = "查询所有ReturnCause",notes = "查询所ReturnCause有方法详情",tags = {"ReturnCauseController"})
    @GetMapping
    public CommonResult<List<ReturnCause>> findAll(){
        //调用ReturnCauseService实现查询所有ReturnCause
        List<ReturnCause> list = returnCauseService.findAll();
        return CommonResult.success(list);
    }
}
