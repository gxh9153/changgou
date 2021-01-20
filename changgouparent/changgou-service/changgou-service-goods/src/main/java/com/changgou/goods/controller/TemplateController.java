package com.changgou.goods.controller;

import com.changgou.api.CommonPage;
import com.changgou.api.CommonResult;
import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @GetMapping("/findByPageAndKW/{page}/{size}")
    public CommonResult<CommonPage<Template>> findByPageAndKW(@PathVariable("page")Integer page,
                                                              @PathVariable("size") Integer size,
                                                              @RequestBody Template template){
        List<Template> list = templateService.findPage(template, page, size);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @GetMapping("/findByPage/{page}/{size}")
    public CommonResult<CommonPage<Template>> findByPage(@PathVariable("page")Integer page,
                                                         @PathVariable("size") Integer size){
        List<Template> list = templateService.findPage(page, size);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @GetMapping("/findByKW")
    public CommonResult<List<Template>> findByKW(@RequestBody Template template){
        List<Template> list = templateService.findList(template);
        return CommonResult.success(list);
    }

    @DeleteMapping("/deleteById/{id}")
    public CommonResult<Integer> deleteById(@PathVariable("id") Integer id){
        int count = templateService.delete(id);
        return CommonResult.success(count);
    }

    @PutMapping("update/{id}")
    public CommonResult<Integer> update(@PathVariable("id") Integer id,
                                        @RequestBody Template template){
        template.setId(id);
        int count = templateService.update(template);
        return CommonResult.success(count);
    }

    @PostMapping("/create")
    public CommonResult<Integer> create(@RequestBody Template template){
        int count = templateService.add(template);
        return CommonResult.success(count);
    }

    @GetMapping("/findById/{id}")
    public CommonResult<Template> findById(@PathVariable("id") Integer id){
        Template template = templateService.findById(id);
        return CommonResult.success(template);
    }

    @GetMapping("/findAll")
    public CommonResult<List<Template>> findAll(){
        List<Template> list = templateService.findAll();
        return CommonResult.success(list);
    }
}
