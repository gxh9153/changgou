package com.changgou.user.controller;

import com.changgou.api.CommonResult;
import com.changgou.user.pojo.Cities;
import com.changgou.user.service.CitiesService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:gxh
 * @Date 2021/1/12
 *****/
@Api(value = "CitiesController")
@RestController
@RequestMapping("/cities")
@CrossOrigin
public class CitiesController {

    @Autowired
    private CitiesService citiesService;

    /***
     * Cities分页条件搜索实现
     * @param cities
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Cities条件分页查询",notes = "分页条件查询Cities方法详情",tags = {"CitiesController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<Cities>> findPage(@RequestBody(required = false) @ApiParam(name = "Cities对象",value = "传入JSON数据",required = false) Cities cities, @PathVariable  int page, @PathVariable  int size){
        //调用CitiesService实现分页条件查询Cities
        List<Cities> list = citiesService.findPage(cities, page, size);
        return CommonResult.success(list);
    }

    /***
     * Cities分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Cities分页查询",notes = "分页查询Cities方法详情",tags = {"CitiesController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<Cities>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用CitiesService实现分页查询Cities
        List<Cities> list = citiesService.findPage(page, size);
        return CommonResult.success(list);
    }

    /***
     * 多条件搜索品牌数据
     * @param cities
     * @return
     */
    @ApiOperation(value = "Cities条件查询",notes = "条件查询Cities方法详情",tags = {"CitiesController"})
    @PostMapping(value = "/search" )
    public CommonResult<List<Cities>> findList(@RequestBody(required = false) @ApiParam(name = "Cities对象",value = "传入JSON数据",required = false) Cities cities){
        //调用CitiesService实现条件查询Cities
        List<Cities> list = citiesService.findList(cities);
        return CommonResult.success(list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Cities根据ID删除",notes = "根据ID删除Cities方法详情",tags = {"CitiesController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping(value = "/{id}" )
    public CommonResult<Integer> delete(@PathVariable String id){
        //调用CitiesService实现根据主键删除
        int count = citiesService.delete(id);
        return CommonResult.success(count);
    }

    /***
     * 修改Cities数据
     * @param cities
     * @param id
     * @return
     */
    @ApiOperation(value = "Cities根据ID修改",notes = "根据ID修改Cities方法详情",tags = {"CitiesController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @PutMapping(value="/{id}")
    public CommonResult<Integer> update(@RequestBody @ApiParam(name = "Cities对象",value = "传入JSON数据",required = false) Cities cities,@PathVariable String id){
        //设置主键值
        cities.setCityid(id);
        //调用CitiesService实现修改Cities
        int count = citiesService.update(cities);
        return CommonResult.success(count);
    }

    /***
     * 新增Cities数据
     * @param cities
     * @return
     */
    @ApiOperation(value = "Cities添加",notes = "添加Cities方法详情",tags = {"CitiesController"})
    @PostMapping
    public CommonResult<Integer> add(@RequestBody  @ApiParam(name = "Cities对象",value = "传入JSON数据",required = true) Cities cities){
        //调用CitiesService实现添加Cities
        int count = citiesService.add(cities);
        return CommonResult.success(count);
    }

    /***
     * 根据ID查询Cities数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Cities根据ID查询",notes = "根据ID查询Cities方法详情",tags = {"CitiesController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @GetMapping("/{id}")
    public CommonResult<Cities> findById(@PathVariable String id){
        //调用CitiesService实现根据主键查询Cities
        Cities cities = citiesService.findById(id);
        return CommonResult.success(cities);
    }

    /***
     * 查询Cities全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Cities",notes = "查询所Cities有方法详情",tags = {"CitiesController"})
    @GetMapping
    public CommonResult<List<Cities>> findAll(){
        //调用CitiesService实现查询所有Cities
        List<Cities> list = citiesService.findAll();
        return CommonResult.success(list);
    }
}
