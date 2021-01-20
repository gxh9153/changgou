package com.changgou.user.controller;

import com.changgou.api.CommonResult;
import com.changgou.user.pojo.Provinces;
import com.changgou.user.service.ProvincesService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:gxh
 * @Date 2021/1/12
 *****/
@Api(value = "ProvincesController")
@RestController
@RequestMapping("/provinces")
@CrossOrigin
public class ProvincesController {

    @Autowired
    private ProvincesService provincesService;

    /***
     * Provinces分页条件搜索实现
     * @param provinces
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Provinces条件分页查询",notes = "分页条件查询Provinces方法详情",tags = {"ProvincesController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<Provinces>> findPage(@RequestBody(required = false) @ApiParam(name = "Provinces对象",value = "传入JSON数据",required = false) Provinces provinces, @PathVariable  int page, @PathVariable  int size){
        //调用ProvincesService实现分页条件查询Provinces
        List<Provinces> list = provincesService.findPage(provinces, page, size);
        return CommonResult.success(list);
    }

    /***
     * Provinces分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Provinces分页查询",notes = "分页查询Provinces方法详情",tags = {"ProvincesController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<Provinces>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用ProvincesService实现分页查询Provinces
        List<Provinces> list = provincesService.findPage(page, size);
        return CommonResult.success(list);
    }

    /***
     * 多条件搜索品牌数据
     * @param provinces
     * @return
     */
    @ApiOperation(value = "Provinces条件查询",notes = "条件查询Provinces方法详情",tags = {"ProvincesController"})
    @PostMapping(value = "/search" )
    public CommonResult<List<Provinces>> findList(@RequestBody(required = false) @ApiParam(name = "Provinces对象",value = "传入JSON数据",required = false) Provinces provinces){
        //调用ProvincesService实现条件查询Provinces
        List<Provinces> list = provincesService.findList(provinces);
        return CommonResult.success(list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Provinces根据ID删除",notes = "根据ID删除Provinces方法详情",tags = {"ProvincesController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping(value = "/{id}" )
    public CommonResult<Integer> delete(@PathVariable String id){
        //调用ProvincesService实现根据主键删除
        int count = provincesService.delete(id);
        return CommonResult.success(count);
    }

    /***
     * 修改Provinces数据
     * @param provinces
     * @param id
     * @return
     */
    @ApiOperation(value = "Provinces根据ID修改",notes = "根据ID修改Provinces方法详情",tags = {"ProvincesController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @PutMapping(value="/{id}")
    public CommonResult<Integer> update(@RequestBody @ApiParam(name = "Provinces对象",value = "传入JSON数据",required = false) Provinces provinces,@PathVariable String id){
        //设置主键值
        provinces.setProvinceid(id);
        //调用ProvincesService实现修改Provinces
        int count = provincesService.update(provinces);
        return CommonResult.success(count);
    }

    /***
     * 新增Provinces数据
     * @param provinces
     * @return
     */
    @ApiOperation(value = "Provinces添加",notes = "添加Provinces方法详情",tags = {"ProvincesController"})
    @PostMapping
    public CommonResult<Integer> add(@RequestBody  @ApiParam(name = "Provinces对象",value = "传入JSON数据",required = true) Provinces provinces){
        //调用ProvincesService实现添加Provinces
        int count = provincesService.add(provinces);
        return CommonResult.success(count);
    }

    /***
     * 根据ID查询Provinces数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Provinces根据ID查询",notes = "根据ID查询Provinces方法详情",tags = {"ProvincesController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @GetMapping("/{id}")
    public CommonResult<Provinces> findById(@PathVariable String id){
        //调用ProvincesService实现根据主键查询Provinces
        Provinces provinces = provincesService.findById(id);
        return CommonResult.success(provinces);
    }

    /***
     * 查询Provinces全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Provinces",notes = "查询所Provinces有方法详情",tags = {"ProvincesController"})
    @GetMapping
    public CommonResult<List<Provinces>> findAll(){
        //调用ProvincesService实现查询所有Provinces
        List<Provinces> list = provincesService.findAll();
        return CommonResult.success(list);
    }
}
