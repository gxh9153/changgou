package com.changgou.goods.controller;

import com.changgou.api.CommonPage;
import com.changgou.api.CommonResult;
import com.changgou.goods.pojo.Pref;
import com.changgou.goods.service.PrefService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/
@Api(value = "PrefController")
@RestController
@RequestMapping("/pref")
@CrossOrigin
public class PrefController {

    @Autowired
    private PrefService prefService;

    /***
     * Pref分页条件搜索实现
     * @param pref
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Pref条件分页查询",notes = "分页条件查询Pref方法详情",tags = {"PrefController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public CommonResult<CommonPage<Pref>> findPage(@RequestBody(required = false) @ApiParam(name = "Pref对象",value = "传入JSON数据",required = false) Pref pref, @PathVariable  int page, @PathVariable  int size){
        //调用PrefService实现分页条件查询Pref
        List<Pref> list = prefService.findPage(pref, page, size);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /***
     * Pref分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Pref分页查询",notes = "分页查询Pref方法详情",tags = {"PrefController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public CommonResult<CommonPage<Pref>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用PrefService实现分页查询Pref
        List<Pref> list = prefService.findPage(page, size);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /***
     * 多条件搜索品牌数据
     * @param pref
     * @return
     */
    @ApiOperation(value = "Pref条件查询",notes = "条件查询Pref方法详情",tags = {"PrefController"})
    @PostMapping(value = "/search" )
    public CommonResult<List<Pref>> findList(@RequestBody(required = false) @ApiParam(name = "Pref对象",value = "传入JSON数据",required = false) Pref pref){
        //调用PrefService实现条件查询Pref
        List<Pref> list = prefService.findList(pref);
        return CommonResult.success(list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Pref根据ID删除",notes = "根据ID删除Pref方法详情",tags = {"PrefController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public CommonResult<Integer> delete(@PathVariable Integer id){
        //调用PrefService实现根据主键删除
        int count = prefService.delete(id);
        return CommonResult.success(count);
    }

    /***
     * 修改Pref数据
     * @param pref
     * @param id
     * @return
     */
    @ApiOperation(value = "Pref根据ID修改",notes = "根据ID修改Pref方法详情",tags = {"PrefController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public CommonResult<Integer> update(@RequestBody @ApiParam(name = "Pref对象",value = "传入JSON数据",required = false) Pref pref,@PathVariable Integer id){
        //设置主键值
        pref.setId(id);
        //调用PrefService实现修改Pref
        int count = prefService.update(pref);
        return CommonResult.success(count);
    }

    /***
     * 新增Pref数据
     * @param pref
     * @return
     */
    @ApiOperation(value = "Pref添加",notes = "添加Pref方法详情",tags = {"PrefController"})
    @PostMapping
    public CommonResult<Integer> add(@RequestBody  @ApiParam(name = "Pref对象",value = "传入JSON数据",required = true) Pref pref){
        //调用PrefService实现添加Pref
        int count = prefService.add(pref);
        return CommonResult.success(count);
    }

    /***
     * 根据ID查询Pref数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Pref根据ID查询",notes = "根据ID查询Pref方法详情",tags = {"PrefController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public CommonResult<Pref> findById(@PathVariable Integer id){
        //调用PrefService实现根据主键查询Pref
        Pref pref = prefService.findById(id);
        return CommonResult.success(pref);
    }

    /***
     * 查询Pref全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Pref",notes = "查询所Pref有方法详情",tags = {"PrefController"})
    @GetMapping
    public CommonResult<List<Pref>> findAll(){
        //调用PrefService实现查询所有Pref
        List<Pref> list = prefService.findAll();
        return CommonResult.success(list);
    }
}
