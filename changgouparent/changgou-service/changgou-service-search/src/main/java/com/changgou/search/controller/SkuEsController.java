package com.changgou.search.controller;

import com.changgou.api.CommonResult;
import com.changgou.search.service.SkuEsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author:gxh
 * @Date: 2021/1/4 17:06
 */
@RestController
@Api(tags = "SkuEsController", description = "商品sku ES搜索")
@RequestMapping("/search")
public class SkuEsController {

    @Autowired
    private SkuEsService skuEsService;


    @ApiOperation("根据关键词搜索")
    @GetMapping
    public Map search(@RequestParam(required = false) Map<String,String> searchMap){
        Map<String, Object> resultMap = skuEsService.search(searchMap);
        return resultMap;
    }

    @ApiOperation("商品sku导入索引库")
    @GetMapping("/import")
    public CommonResult importData(){
        skuEsService.importData();
        return CommonResult.success("true","导入索引库成功");
    }
}
