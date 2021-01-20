package com.changgou.orders.controller;

import com.changgou.api.CommonResult;
import com.changgou.orders.pojo.CategoryReport;
import com.changgou.orders.service.CategoryReportService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/****
 * @Author:gxh
 * @Date 2021/1/12
 *****/
@Api(value = "CategoryReportController")
@RestController
@RequestMapping("/categoryReport")
@CrossOrigin
public class CategoryReportController {

    @Autowired
    private CategoryReportService categoryReportService;

    /***
     * CategoryReport分页条件搜索实现
     * @param categoryReport
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "CategoryReport条件分页查询",notes = "分页条件查询CategoryReport方法详情",tags = {"CategoryReportController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<CategoryReport>> findPage(@RequestBody(required = false) @ApiParam(name = "CategoryReport对象",value = "传入JSON数据",required = false) CategoryReport categoryReport, @PathVariable  int page, @PathVariable  int size){
        //调用CategoryReportService实现分页条件查询CategoryReport
        List<CategoryReport> list = categoryReportService.findPage(categoryReport, page, size);
        return CommonResult.success(list);
    }

    /***
     * CategoryReport分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "CategoryReport分页查询",notes = "分页查询CategoryReport方法详情",tags = {"CategoryReportController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<CategoryReport>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用CategoryReportService实现分页查询CategoryReport
        List<CategoryReport> list = categoryReportService.findPage(page, size);
        return CommonResult.success(list);
    }

    /***
     * 多条件搜索品牌数据
     * @param categoryReport
     * @return
     */
    @ApiOperation(value = "CategoryReport条件查询",notes = "条件查询CategoryReport方法详情",tags = {"CategoryReportController"})
    @PostMapping(value = "/search" )
    public CommonResult<List<CategoryReport>> findList(@RequestBody(required = false) @ApiParam(name = "CategoryReport对象",value = "传入JSON数据",required = false) CategoryReport categoryReport){
        //调用CategoryReportService实现条件查询CategoryReport
        List<CategoryReport> list = categoryReportService.findList(categoryReport);
        return CommonResult.success(list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "CategoryReport根据ID删除",notes = "根据ID删除CategoryReport方法详情",tags = {"CategoryReportController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Date")
    @DeleteMapping(value = "/{id}" )
    public CommonResult<Integer> delete(@PathVariable Date id){
        //调用CategoryReportService实现根据主键删除
        int count = categoryReportService.delete(id);
        return CommonResult.success(count);
    }

    /***
     * 修改CategoryReport数据
     * @param categoryReport
     * @param id
     * @return
     */
    @ApiOperation(value = "CategoryReport根据ID修改",notes = "根据ID修改CategoryReport方法详情",tags = {"CategoryReportController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Date")
    @PutMapping(value="/{id}")
    public CommonResult<Integer> update(@RequestBody @ApiParam(name = "CategoryReport对象",value = "传入JSON数据",required = false) CategoryReport categoryReport,@PathVariable Date id){
        //设置主键值
        categoryReport.setCountDate(id);
        //调用CategoryReportService实现修改CategoryReport
        int count = categoryReportService.update(categoryReport);
        return CommonResult.success(count);
    }

    /***
     * 新增CategoryReport数据
     * @param categoryReport
     * @return
     */
    @ApiOperation(value = "CategoryReport添加",notes = "添加CategoryReport方法详情",tags = {"CategoryReportController"})
    @PostMapping
    public CommonResult<Integer> add(@RequestBody  @ApiParam(name = "CategoryReport对象",value = "传入JSON数据",required = true) CategoryReport categoryReport){
        //调用CategoryReportService实现添加CategoryReport
        int count = categoryReportService.add(categoryReport);
        return CommonResult.success(count);
    }

    /***
     * 根据ID查询CategoryReport数据
     * @param id
     * @return
     */
    @ApiOperation(value = "CategoryReport根据ID查询",notes = "根据ID查询CategoryReport方法详情",tags = {"CategoryReportController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Date")
    @GetMapping("/{id}")
    public CommonResult<CategoryReport> findById(@PathVariable Date id){
        //调用CategoryReportService实现根据主键查询CategoryReport
        CategoryReport categoryReport = categoryReportService.findById(id);
        return CommonResult.success(categoryReport);
    }

    /***
     * 查询CategoryReport全部数据
     * @return
     */
    @ApiOperation(value = "查询所有CategoryReport",notes = "查询所CategoryReport有方法详情",tags = {"CategoryReportController"})
    @GetMapping
    public CommonResult<List<CategoryReport>> findAll(){
        //调用CategoryReportService实现查询所有CategoryReport
        List<CategoryReport> list = categoryReportService.findAll();
        return CommonResult.success(list);
    }
}
