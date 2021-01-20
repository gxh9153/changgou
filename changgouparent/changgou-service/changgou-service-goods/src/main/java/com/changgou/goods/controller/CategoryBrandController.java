package com.changgou.goods.controller;

import com.changgou.api.CommonPage;
import com.changgou.api.CommonResult;
import com.changgou.goods.pojo.CategoryBrand;
import com.changgou.goods.service.CategoryBrandService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/
@Api(value = "CategoryBrandController")
@RestController
@RequestMapping("/categoryBrand")
@CrossOrigin
public class CategoryBrandController {

    @Autowired
    private CategoryBrandService categoryBrandService;

    /***
     * CategoryBrand分页条件搜索实现
     * @param categoryBrand
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "CategoryBrand条件分页查询",notes = "分页条件查询CategoryBrand方法详情",tags = {"CategoryBrandController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public CommonResult<CommonPage<CategoryBrand>> findPage(@RequestBody(required = false) @ApiParam(name = "CategoryBrand对象",value = "传入JSON数据",required = false) CategoryBrand categoryBrand, @PathVariable  int page, @PathVariable  int size){
        //调用CategoryBrandService实现分页条件查询CategoryBrand
        List<CategoryBrand> list = categoryBrandService.findPage(categoryBrand, page, size);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /***
     * CategoryBrand分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "CategoryBrand分页查询",notes = "分页查询CategoryBrand方法详情",tags = {"CategoryBrandController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public CommonResult<CommonPage<CategoryBrand>>  findPage(@PathVariable  int page, @PathVariable  int size){
        //调用CategoryBrandService实现分页查询CategoryBrand
        List<CategoryBrand> list = categoryBrandService.findPage(page, size);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /***
     * 多条件搜索品牌数据
     * @param categoryBrand
     * @return
     */
    @ApiOperation(value = "CategoryBrand条件查询",notes = "条件查询CategoryBrand方法详情",tags = {"CategoryBrandController"})
    @PostMapping(value = "/search" )
    public CommonResult<List<CategoryBrand>> findList(@RequestBody(required = false) @ApiParam(name = "CategoryBrand对象",value = "传入JSON数据",required = false) CategoryBrand categoryBrand){
        //调用CategoryBrandService实现条件查询CategoryBrand
        List<CategoryBrand> list = categoryBrandService.findList(categoryBrand);
        return CommonResult.success(list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "CategoryBrand根据ID删除",notes = "根据ID删除CategoryBrand方法详情",tags = {"CategoryBrandController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public CommonResult<Integer> delete(@PathVariable Integer id){
        //调用CategoryBrandService实现根据主键删除
        int count = categoryBrandService.delete(id);
        return CommonResult.success(count);
    }

    /***
     * 修改CategoryBrand数据
     * @param categoryBrand
     * @param id
     * @return
     */
    @ApiOperation(value = "CategoryBrand根据ID修改",notes = "根据ID修改CategoryBrand方法详情",tags = {"CategoryBrandController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public CommonResult<Integer> update(@RequestBody @ApiParam(name = "CategoryBrand对象",value = "传入JSON数据",required = false) CategoryBrand categoryBrand,@PathVariable Integer id){
        //设置主键值
        categoryBrand.setCategoryId(id);
        //调用CategoryBrandService实现修改CategoryBrand
        int count = categoryBrandService.update(categoryBrand);
        return CommonResult.success(count);
    }

    /***
     * 新增CategoryBrand数据
     * @param categoryBrand
     * @return
     */
    @ApiOperation(value = "CategoryBrand添加",notes = "添加CategoryBrand方法详情",tags = {"CategoryBrandController"})
    @PostMapping
    public CommonResult<Integer> add(@RequestBody  @ApiParam(name = "CategoryBrand对象",value = "传入JSON数据",required = true) CategoryBrand categoryBrand){
        //调用CategoryBrandService实现添加CategoryBrand
        int count = categoryBrandService.add(categoryBrand);
        return CommonResult.success(count);
    }

    /***
     * 根据ID查询CategoryBrand数据
     * @param id
     * @return
     */
    @ApiOperation(value = "CategoryBrand根据ID查询",notes = "根据ID查询CategoryBrand方法详情",tags = {"CategoryBrandController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public CommonResult<CategoryBrand> findById(@PathVariable Integer id){
        //调用CategoryBrandService实现根据主键查询CategoryBrand
        CategoryBrand categoryBrand = categoryBrandService.findById(id);
        return CommonResult.success(categoryBrand);
    }

    /***
     * 查询CategoryBrand全部数据
     * @return
     */
    @ApiOperation(value = "查询所有CategoryBrand",notes = "查询所CategoryBrand有方法详情",tags = {"CategoryBrandController"})
    @GetMapping
    public CommonResult<List<CategoryBrand>> findAll(){
        //调用CategoryBrandService实现查询所有CategoryBrand
        List<CategoryBrand> list = categoryBrandService.findAll();
        return CommonResult.success(list) ;
    }
}
