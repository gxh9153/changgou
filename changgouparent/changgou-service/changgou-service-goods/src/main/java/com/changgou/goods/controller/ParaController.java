package com.changgou.goods.controller;

import com.changgou.api.CommonPage;
import com.changgou.api.CommonResult;
import com.changgou.goods.pojo.Para;
import com.changgou.goods.service.ParaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/para")
public class ParaController {

    @Autowired
    private ParaService paraService;

    @GetMapping("/findByPageAndKW/{page}/{size}")
    public CommonResult<CommonPage<Para>> findByPageAndKW(@PathVariable("page") Integer page,
                                                          @PathVariable("size")  Integer size,
                                                          @RequestBody Para para){
        List<Para> list = paraService.findPage(para, page, size);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @GetMapping("/findByPage/{page}/{size}")
    public CommonResult<CommonPage<Para>> findByPage(@PathVariable("page") Integer page,
                                                     @PathVariable("size")  Integer size){
        List<Para> list = paraService.findPage(page, size);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @GetMapping("/findByKW")
    public CommonResult<List<Para>> findByKW(@RequestBody Para para){
        List<Para> list = paraService.findList(para);
        return CommonResult.success(list);
    }

    @DeleteMapping("/deleteById/{id}")
    public CommonResult<Integer> delete(@PathVariable("id") Integer id){
        int count = paraService.delete(id);
        return CommonResult.success(count);
    }

    @PutMapping("/update/{id}")
    public CommonResult<Integer> update(@PathVariable("id") Integer id,
                                        @RequestBody Para para){
        para.setId(id);
        int count = paraService.update(para);
        return CommonResult.success(count);
    }

    @PostMapping("/create")
    public CommonResult<Integer> create(@RequestBody Para para){
        int count = paraService.add(para);
        return CommonResult.success(count);
    }

    @GetMapping("/findById/{id}")
    public CommonResult<Para> findById(@PathVariable("id") Integer id){
        Para para = paraService.findById(id);
        return CommonResult.success(para);
    }

    @GetMapping("/findAll")
    public CommonResult<List<Para>> findAll(){
        List<Para> list = paraService.findAll();
        return CommonResult.success(list);
    }

    @GetMapping("/findByCategory/{categoryId}")
    public CommonResult<List<Para>> findByCategory(@PathVariable("categoryId") Integer categoryId){
        List<Para> list = paraService.findByCategory(categoryId);
        return CommonResult.success(list);
    }
}
