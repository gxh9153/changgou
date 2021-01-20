package com.changgou.goods.controller;
import com.changgou.api.CommonPage;
import com.changgou.api.CommonResult;
import com.changgou.goods.dto.Goods;
import com.changgou.goods.pojo.Spu;
import com.changgou.goods.service.SpuService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/
@Api(value = "SpuController")
@RestController
@RequestMapping("/spu")
@CrossOrigin
public class SpuController {

    @Autowired
    private SpuService spuService;

    /***
     * Spu分页条件搜索实现
     * @param spu
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Spu条件分页查询",notes = "分页条件查询Spu方法详情",tags = {"SpuController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public CommonResult<CommonPage<Spu>> findPage(@RequestBody(required = false) @ApiParam(name = "Spu对象",value = "传入JSON数据",required = false) Spu spu, @PathVariable  int page, @PathVariable  int size){
        //调用SpuService实现分页条件查询Spu
        List<Spu> list = spuService.findPage(spu, page, size);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /***
     * Spu分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Spu分页查询",notes = "分页查询Spu方法详情",tags = {"SpuController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public CommonResult<CommonPage<Spu>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用SpuService实现分页查询Spu
        List<Spu> list = spuService.findPage(page, size);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /***
     * 多条件搜索品牌数据
     * @param spu
     * @return
     */
    @ApiOperation(value = "Spu条件查询",notes = "条件查询Spu方法详情",tags = {"SpuController"})
    @PostMapping(value = "/search" )
    public CommonResult<List<Spu>> findList(@RequestBody(required = false) @ApiParam(name = "Spu对象",value = "传入JSON数据",required = false) Spu spu){
        //调用SpuService实现条件查询Spu
        List<Spu> list = spuService.findList(spu);
        return CommonResult.success(list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Spu根据ID删除",notes = "根据ID删除Spu方法详情",tags = {"SpuController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @DeleteMapping(value = "/{id}" )
    public CommonResult<Integer> delete(@PathVariable Long id){
        //调用SpuService实现根据主键删除
        int count = spuService.delete(id);
        return CommonResult.success(count);
    }

    /***
     * 修改Spu数据
     * @param spu
     * @param id
     * @return
     */
    @ApiOperation(value = "Spu根据ID修改",notes = "根据ID修改Spu方法详情",tags = {"SpuController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @PutMapping(value="/{id}")
    public CommonResult<Integer> update(@RequestBody @ApiParam(name = "Spu对象",value = "传入JSON数据",required = false) Spu spu,@PathVariable Long id){
        //设置主键值
        spu.setId(id);
        //调用SpuService实现修改Spu
        int count = spuService.update(spu);
        return CommonResult.success(count);
    }

    /***
     * 新增Spu数据
     * @param spu
     * @return
     */
    @ApiOperation(value = "Spu添加",notes = "添加Spu方法详情",tags = {"SpuController"})
    @PostMapping
    public CommonResult<Integer> add(@RequestBody  @ApiParam(name = "Spu对象",value = "传入JSON数据",required = true) Spu spu){
        //调用SpuService实现添加Spu
        int count = spuService.add(spu);
        return CommonResult.success(count);
    }

    /***
     * 根据ID查询Spu数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Spu根据ID查询",notes = "根据ID查询Spu方法详情",tags = {"SpuController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public CommonResult<Spu> findById(@PathVariable Long id){
        //调用SpuService实现根据主键查询Spu
        Spu spu = spuService.findById(id);
        return CommonResult.success(spu);
    }

    /***
     * 查询Spu全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Spu",notes = "查询所Spu有方法详情",tags = {"SpuController"})
    @GetMapping
    public CommonResult<List<Spu>> findAll(){
        //调用SpuService实现查询所有Spu
        List<Spu> list = spuService.findAll();
        return CommonResult.success(list) ;
    }

    /**
     * 添加商品
     * @param goods
     * @return
     */
    @PostMapping("/save")
    public CommonResult<Integer> createGoods(@RequestBody Goods goods){
        int count = spuService.createGoods(goods);
        return CommonResult.success(count);
    }

    /**
     * 修改商品
     * @param id
     * @param goods
     * @return
     */
    @PutMapping("/update/{id}")
    public CommonResult<Integer> updateGoods(@PathVariable("id") Long id,@RequestBody Goods goods){
        Spu spu = goods.getSpu();
        spu.setId(id);
        int count = spuService.createGoods(goods);
        return CommonResult.success(count);
    }

    /**
     * 根据spuId查找商品
     * @param id
     * @return
     */
    @GetMapping("/goods/{id}")
    public CommonResult<Goods> findGoodsById(@PathVariable("id") Long id){
        Goods goods = spuService.findGoodsBySpuId(id);
        return CommonResult.success(goods);
    }

    /**
     * 审核产品
     * @param id
     * @return
     */
    @PostMapping("/audit/{id]")
    public CommonResult<Integer> audit(@PathVariable("id") Long id){
        int count = spuService.audit(id);
        return CommonResult.success(count);
    }

    /**
     * 上架产品
     * @param id
     * @return
     */
        @PostMapping("/put/{id}")
    public CommonResult<Integer> put(@PathVariable("id")Long id){
        int count = spuService.shelves(id);
        return CommonResult.success(count);
    }

    /**
     * 下架产品
     * @param id
     * @return
     */
    @PostMapping("/pull/{id}")
    public CommonResult<Integer> pull(@PathVariable("id") Long id){
        int count = spuService.undercarriage(id);
        return CommonResult.success(count);
    }

    /**
     * 批量上架商品
     * @param ids
     * @return
     */
    @PostMapping("/putMany")
    public CommonResult<Integer> putMany(@RequestParam("ids") List<Long> ids){
        int count = spuService.putMany(ids);
        if(count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }

    }

    /**
     * 批量下架产品
     * @param ids
     * @return
     */
    @PostMapping("/pullMany")
    public CommonResult<Integer> pullMany(@RequestParam("ids") List<Long> ids){
        int count = spuService.pushMany(ids);
        if(count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }
}
