package com.changgou.user.controller;

import com.changgou.api.CommonResult;
import com.changgou.user.pojo.Areas;
import com.changgou.user.service.AreasService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:gxh
 * @Date 2021/1/12
 *****/
@Api(value = "AreasController")
@RestController
@RequestMapping("/areas")
@CrossOrigin
public class AreasController {

    @Autowired
    private AreasService areasService;

    /***
     * Areas分页条件搜索实现
     * @param areas
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Areas条件分页查询",notes = "分页条件查询Areas方法详情",tags = {"AreasController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<Areas>> findPage(@RequestBody(required = false) @ApiParam(name = "Areas对象",value = "传入JSON数据",required = false) Areas areas, @PathVariable  int page, @PathVariable  int size){
        //调用AreasService实现分页条件查询Areas
        List<Areas> list = areasService.findPage(areas, page, size);
        return CommonResult.success(list);
    }

    /***
     * Areas分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Areas分页查询",notes = "分页查询Areas方法详情",tags = {"AreasController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<Areas>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用AreasService实现分页查询Areas
        List<Areas> list = areasService.findPage(page, size);
        return CommonResult.success(list);
    }

    /***
     * 多条件搜索品牌数据
     * @param areas
     * @return
     */
    @ApiOperation(value = "Areas条件查询",notes = "条件查询Areas方法详情",tags = {"AreasController"})
    @PostMapping(value = "/search" )
    public CommonResult<List<Areas>> findList(@RequestBody(required = false) @ApiParam(name = "Areas对象",value = "传入JSON数据",required = false) Areas areas){
        //调用AreasService实现条件查询Areas
        List<Areas> list = areasService.findList(areas);
        return CommonResult.success(list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Areas根据ID删除",notes = "根据ID删除Areas方法详情",tags = {"AreasController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping(value = "/{id}" )
    public CommonResult<Integer> delete(@PathVariable String id){
        //调用AreasService实现根据主键删除
        int count = areasService.delete(id);
        return CommonResult.success(count);
    }

    /***
     * 修改Areas数据
     * @param areas
     * @param id
     * @return
     */
    @ApiOperation(value = "Areas根据ID修改",notes = "根据ID修改Areas方法详情",tags = {"AreasController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @PutMapping(value="/{id}")
    public CommonResult<Integer> update(@RequestBody @ApiParam(name = "Areas对象",value = "传入JSON数据",required = false) Areas areas,@PathVariable String id){
        //设置主键值
        areas.setAreaid(id);
        //调用AreasService实现修改Areas
        int count = areasService.update(areas);
        return CommonResult.success(count);
    }

    /***
     * 新增Areas数据
     * @param areas
     * @return
     */
    @ApiOperation(value = "Areas添加",notes = "添加Areas方法详情",tags = {"AreasController"})
    @PostMapping
    public CommonResult<Integer> add(@RequestBody  @ApiParam(name = "Areas对象",value = "传入JSON数据",required = true) Areas areas){
        //调用AreasService实现添加Areas
        int count = areasService.add(areas);
        return CommonResult.success(count);
    }

    /***
     * 根据ID查询Areas数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Areas根据ID查询",notes = "根据ID查询Areas方法详情",tags = {"AreasController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @GetMapping("/{id}")
    public CommonResult<Areas> findById(@PathVariable String id){
        //调用AreasService实现根据主键查询Areas
        Areas areas = areasService.findById(id);
        return CommonResult.success(areas);
    }

    /***
     * 查询Areas全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Areas",notes = "查询所Areas有方法详情",tags = {"AreasController"})
    @GetMapping
    public CommonResult<List<Areas>> findAll(){
        //调用AreasService实现查询所有Areas
        List<Areas> list = areasService.findAll();
        return CommonResult.success(list);
    }
}
