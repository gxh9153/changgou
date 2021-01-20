package com.changgou.goods.controller;
import com.changgou.api.CommonPage;
import com.changgou.api.CommonResult;
import com.changgou.goods.pojo.StockBack;
import com.changgou.goods.service.StockBackService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/
@Api(value = "StockBackController")
@RestController
@RequestMapping("/stockBack")
@CrossOrigin
public class StockBackController {

    @Autowired
    private StockBackService stockBackService;

    /***
     * StockBack分页条件搜索实现
     * @param stockBack
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "StockBack条件分页查询",notes = "分页条件查询StockBack方法详情",tags = {"StockBackController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public CommonResult<CommonPage<StockBack>> findPage(@RequestBody(required = false) @ApiParam(name = "StockBack对象",value = "传入JSON数据",required = false) StockBack stockBack, @PathVariable  int page, @PathVariable  int size){
        //调用StockBackService实现分页条件查询StockBack
        List<StockBack> list = stockBackService.findPage(stockBack, page, size);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /***
     * StockBack分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "StockBack分页查询",notes = "分页查询StockBack方法详情",tags = {"StockBackController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public CommonResult<CommonPage<StockBack>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用StockBackService实现分页查询StockBack
        List<StockBack> list = stockBackService.findPage(page, size);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /***
     * 多条件搜索品牌数据
     * @param stockBack
     * @return
     */
    @ApiOperation(value = "StockBack条件查询",notes = "条件查询StockBack方法详情",tags = {"StockBackController"})
    @PostMapping(value = "/search" )
    public CommonResult<List<StockBack>> findList(@RequestBody(required = false) @ApiParam(name = "StockBack对象",value = "传入JSON数据",required = false) StockBack stockBack){
        //调用StockBackService实现条件查询StockBack
        List<StockBack> list = stockBackService.findList(stockBack);
        return CommonResult.success(list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "StockBack根据ID删除",notes = "根据ID删除StockBack方法详情",tags = {"StockBackController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping(value = "/{id}" )
    public CommonResult<Integer> delete(@PathVariable String id){
        //调用StockBackService实现根据主键删除
        int count = stockBackService.delete(id);
        return CommonResult.success(count);
    }

    /***
     * 修改StockBack数据
     * @param stockBack
     * @param id
     * @return
     */
    @ApiOperation(value = "StockBack根据ID修改",notes = "根据ID修改StockBack方法详情",tags = {"StockBackController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @PutMapping(value="/{id}")
    public CommonResult<Integer> update(@RequestBody @ApiParam(name = "StockBack对象",value = "传入JSON数据",required = false) StockBack stockBack,@PathVariable String id){
        //设置主键值
        stockBack.setSkuId(id);
        //调用StockBackService实现修改StockBack
        int count = stockBackService.update(stockBack);
        return CommonResult.success(count);
    }

    /***
     * 新增StockBack数据
     * @param stockBack
     * @return
     */
    @ApiOperation(value = "StockBack添加",notes = "添加StockBack方法详情",tags = {"StockBackController"})
    @PostMapping
    public CommonResult<Integer> add(@RequestBody  @ApiParam(name = "StockBack对象",value = "传入JSON数据",required = true) StockBack stockBack){
        //调用StockBackService实现添加StockBack
        int count = stockBackService.add(stockBack);
        return CommonResult.success(count);
    }

    /***
     * 根据ID查询StockBack数据
     * @param id
     * @return
     */
    @ApiOperation(value = "StockBack根据ID查询",notes = "根据ID查询StockBack方法详情",tags = {"StockBackController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @GetMapping("/{id}")
    public CommonResult<StockBack> findById(@PathVariable String id){
        //调用StockBackService实现根据主键查询StockBack
        StockBack stockBack = stockBackService.findById(id);
        return CommonResult.success(stockBack);
    }

    /***
     * 查询StockBack全部数据
     * @return
     */
    @ApiOperation(value = "查询所有StockBack",notes = "查询所StockBack有方法详情",tags = {"StockBackController"})
    @GetMapping
    public CommonResult<List<StockBack>> findAll(){
        //调用StockBackService实现查询所有StockBack
        List<StockBack> list = stockBackService.findAll();
        return CommonResult.success(list);
    }
}
