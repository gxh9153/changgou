package com.changgou.goods.controller;

import com.changgou.api.CommonPage;
import com.changgou.api.CommonResult;
import com.changgou.goods.pojo.Spec;
import com.changgou.goods.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spec")
public class SpecController {

    @Autowired
    private SpecService specService;

    @GetMapping("/findByPageAndKW/{page}/{size}")
    public CommonResult<CommonPage<Spec>> findByPageAndKW(@PathVariable("page") Integer page,
                                                          @PathVariable("size") Integer size,
                                                          @RequestBody Spec spec){
        List<Spec> list = specService.findPage(spec, page, size);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @GetMapping("/findByPage/{page}/{size}")
    public CommonResult<CommonPage<Spec>> findByPage(@PathVariable("page") Integer page,
                                                     @PathVariable("size") Integer size){
        List<Spec> list = specService.findPage(page, size);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @GetMapping("/findByKW")
    public CommonResult<List<Spec>> findByKW(@RequestBody Spec spec){
        List<Spec> list = specService.findList(spec);
        return CommonResult.success(list);
    }

    @DeleteMapping("/deleteById/{id}")
    public CommonResult<Integer> deleteById(@PathVariable("id") Integer id){
        int count = specService.delete(id);
        return CommonResult.success(count);
    }

    @PutMapping("/update/{id}")
    public CommonResult<Integer> update(@PathVariable("id") Integer id,
                                        @RequestBody Spec spec){
        spec.setId(id);
        int count = specService.update(spec);
        return CommonResult.success(count);
    }

    @PostMapping("/create")
    public CommonResult<Integer> create(@RequestBody Spec spec){
        int count = specService.add(spec);
        return CommonResult.success(count);
    }

    @GetMapping("/findById/{id}")
    public CommonResult<Spec> findById(@PathVariable("id") Integer id){
        Spec spec = specService.findById(id);
        return CommonResult.success(spec);
    }

    @GetMapping("/findAll")
    public CommonResult<List<Spec>> findAll(){
        List<Spec> list = specService.findAll();
        return CommonResult.success(list);
    }

    @GetMapping("/findByCategory/{categoryId}")
    public CommonResult<List<Spec>> findByCategory(@PathVariable("categoryId") Integer categoryId){
        List<Spec> list = specService.findByCategory(categoryId);
        return CommonResult.success(list);
    }
}
