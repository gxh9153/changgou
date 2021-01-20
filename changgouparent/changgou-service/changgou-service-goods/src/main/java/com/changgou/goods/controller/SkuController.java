package com.changgou.goods.controller;

import com.changgou.api.CommonPage;
import com.changgou.api.CommonResult;
import com.changgou.goods.pojo.Sku;
import com.changgou.goods.service.SkuService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/
@Api(value = "SkuController")
@RestController
@RequestMapping("/sku")
@CrossOrigin
public class SkuController {

    @Autowired
    private SkuService skuService;

    /***
     * Sku分页条件搜索实现
     * @param sku
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Sku条件分页查询",notes = "分页条件查询Sku方法详情",tags = {"SkuController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public CommonResult<CommonPage<Sku>> findPage(@RequestBody(required = false) @ApiParam(name = "Sku对象",value = "传入JSON数据",required = false) Sku sku, @PathVariable  int page, @PathVariable  int size){
        //调用SkuService实现分页条件查询Sku
        List<Sku> list = skuService.findPage(sku, page, size);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /***
     * Sku分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Sku分页查询",notes = "分页查询Sku方法详情",tags = {"SkuController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public CommonResult<CommonPage<Sku>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用SkuService实现分页查询Sku
        List<Sku> list = skuService.findPage(page, size);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /***
     * 多条件搜索品牌数据
     * @param sku
     * @return
     */
    @ApiOperation(value = "Sku条件查询",notes = "条件查询Sku方法详情",tags = {"SkuController"})
    @PostMapping(value = "/search" )
    public CommonResult<List<Sku>> findList(@RequestBody(required = false) @ApiParam(name = "Sku对象",value = "传入JSON数据",required = false) Sku sku){
        //调用SkuService实现条件查询Sku
        List<Sku> list = skuService.findList(sku);
        return CommonResult.success(list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Sku根据ID删除",notes = "根据ID删除Sku方法详情",tags = {"SkuController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @DeleteMapping(value = "/{id}" )
    public CommonResult<Integer> delete(@PathVariable Long id){
        //调用SkuService实现根据主键删除
        int count = skuService.delete(id);
        return CommonResult.success(count);
    }

    /***
     * 修改Sku数据
     * @param sku
     * @param id
     * @return
     */
    @ApiOperation(value = "Sku根据ID修改",notes = "根据ID修改Sku方法详情",tags = {"SkuController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @PutMapping(value="/{id}")
    public CommonResult<Integer> update(@RequestBody @ApiParam(name = "Sku对象",value = "传入JSON数据",required = false) Sku sku,@PathVariable Long id){
        //设置主键值
        sku.setId(id);
        //调用SkuService实现修改Sku
        int count = skuService.update(sku);
        return CommonResult.success(count);
    }

    /***
     * 新增Sku数据
     * @param sku
     * @return
     */
    @ApiOperation(value = "Sku添加",notes = "添加Sku方法详情",tags = {"SkuController"})
    @PostMapping
    public CommonResult<Integer> add(@RequestBody  @ApiParam(name = "Sku对象",value = "传入JSON数据",required = true) Sku sku){
        //调用SkuService实现添加Sku
        int count = skuService.add(sku);
        return CommonResult.success(count);
    }

    /***
     * 根据ID查询Sku数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Sku根据ID查询",notes = "根据ID查询Sku方法详情",tags = {"SkuController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public CommonResult<Sku> findById(@PathVariable Long id){
        //调用SkuService实现根据主键查询Sku
        Sku sku = skuService.findById(id);
        return CommonResult.success(sku);
    }

    /***
     * 查询Sku全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Sku",notes = "查询所Sku有方法详情",tags = {"SkuController"})
    @GetMapping
    public CommonResult<List<Sku>> findAll(){
        //调用SkuService实现查询所有Sku
        List<Sku> list = skuService.findAll();
        return CommonResult.success(list);
    }
}
