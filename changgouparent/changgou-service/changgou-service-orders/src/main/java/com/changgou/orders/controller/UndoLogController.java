package com.changgou.orders.controller;

import com.changgou.api.CommonResult;
import com.changgou.orders.pojo.UndoLog;
import com.changgou.orders.service.UndoLogService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:gxh
 * @Date 2021/1/12
 *****/
@Api(value = "UndoLogController")
@RestController
@RequestMapping("/undoLog")
@CrossOrigin
public class UndoLogController {

    @Autowired
    private UndoLogService undoLogService;

    /***
     * UndoLog分页条件搜索实现
     * @param undoLog
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "UndoLog条件分页查询",notes = "分页条件查询UndoLog方法详情",tags = {"UndoLogController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<UndoLog>> findPage(@RequestBody(required = false) @ApiParam(name = "UndoLog对象",value = "传入JSON数据",required = false) UndoLog undoLog, @PathVariable  int page, @PathVariable  int size){
        //调用UndoLogService实现分页条件查询UndoLog
        List<UndoLog> list = undoLogService.findPage(undoLog, page, size);
        return CommonResult.success(list);
    }

    /***
     * UndoLog分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "UndoLog分页查询",notes = "分页查询UndoLog方法详情",tags = {"UndoLogController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<UndoLog>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用UndoLogService实现分页查询UndoLog
        List<UndoLog> list = undoLogService.findPage(page, size);
        return CommonResult.success(list);
    }

    /***
     * 多条件搜索品牌数据
     * @param undoLog
     * @return
     */
    @ApiOperation(value = "UndoLog条件查询",notes = "条件查询UndoLog方法详情",tags = {"UndoLogController"})
    @PostMapping(value = "/search" )
    public CommonResult<List<UndoLog>> findList(@RequestBody(required = false) @ApiParam(name = "UndoLog对象",value = "传入JSON数据",required = false) UndoLog undoLog){
        //调用UndoLogService实现条件查询UndoLog
        List<UndoLog> list = undoLogService.findList(undoLog);
        return CommonResult.success(list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "UndoLog根据ID删除",notes = "根据ID删除UndoLog方法详情",tags = {"UndoLogController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @DeleteMapping(value = "/{id}" )
    public CommonResult<Integer> delete(@PathVariable Long id){
        //调用UndoLogService实现根据主键删除
        int count = undoLogService.delete(id);
        return CommonResult.success(count);
    }

    /***
     * 修改UndoLog数据
     * @param undoLog
     * @param id
     * @return
     */
    @ApiOperation(value = "UndoLog根据ID修改",notes = "根据ID修改UndoLog方法详情",tags = {"UndoLogController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @PutMapping(value="/{id}")
    public CommonResult<Integer> update(@RequestBody @ApiParam(name = "UndoLog对象",value = "传入JSON数据",required = false) UndoLog undoLog,@PathVariable Long id){
        //设置主键值
        undoLog.setId(id);
        //调用UndoLogService实现修改UndoLog
        int count = undoLogService.update(undoLog);
        return CommonResult.success(count);
    }

    /***
     * 新增UndoLog数据
     * @param undoLog
     * @return
     */
    @ApiOperation(value = "UndoLog添加",notes = "添加UndoLog方法详情",tags = {"UndoLogController"})
    @PostMapping
    public CommonResult<Integer> add(@RequestBody  @ApiParam(name = "UndoLog对象",value = "传入JSON数据",required = true) UndoLog undoLog){
        //调用UndoLogService实现添加UndoLog
        int count = undoLogService.add(undoLog);
        return CommonResult.success(count);
    }

    /***
     * 根据ID查询UndoLog数据
     * @param id
     * @return
     */
    @ApiOperation(value = "UndoLog根据ID查询",notes = "根据ID查询UndoLog方法详情",tags = {"UndoLogController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public CommonResult<UndoLog> findById(@PathVariable Long id){
        //调用UndoLogService实现根据主键查询UndoLog
        UndoLog undoLog = undoLogService.findById(id);
        return CommonResult.success(undoLog);
    }

    /***
     * 查询UndoLog全部数据
     * @return
     */
    @ApiOperation(value = "查询所有UndoLog",notes = "查询所UndoLog有方法详情",tags = {"UndoLogController"})
    @GetMapping
    public CommonResult<List<UndoLog>> findAll(){
        //调用UndoLogService实现查询所有UndoLog
        List<UndoLog> list = undoLogService.findAll();
        return CommonResult.success(list);
    }
}
