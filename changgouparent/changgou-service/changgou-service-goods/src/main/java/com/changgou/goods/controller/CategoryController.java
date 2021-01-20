package com.changgou.goods.controller;

import com.changgou.api.CommonPage;
import com.changgou.api.CommonResult;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/findByPageAndKW/{page}/{size}")
    public CommonResult<CommonPage<Category>> findByPageAndKW(@PathVariable("page") Integer page,
                                                              @PathVariable("size")  Integer size,
                                                              @RequestBody Category category){
        List<Category> list = categoryService.findPage(category, page, size);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @GetMapping("/findByPage/{page}/{size}")
    public CommonResult<CommonPage<Category>> findByPage(@PathVariable("page") Integer page,
                                                         @PathVariable("size")  Integer size){
        List<Category> list = categoryService.findPage(page, size);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @GetMapping("/findByKW")
    public CommonResult<List<Category>> findByKW(@RequestBody Category category){
        List<Category> list = categoryService.findList(category);
        return CommonResult.success(list);
    }

    @DeleteMapping("/deleteById/{id}")
    public CommonResult<Integer> deleteById(@PathVariable("id") Integer id){
        int count = categoryService.delete(id);
        return CommonResult.success(count);
    }

    @PutMapping("/update/{id}")
    public CommonResult<Integer> update(@PathVariable("id") Integer id,
                                        @RequestBody Category category){
        category.setId(id);
        int count = categoryService.update(category);
        return CommonResult.success(count);
    }

    @PostMapping("/create")
    public CommonResult<Integer> create(@RequestBody Category category){
        int count = categoryService.add(category);
        return CommonResult.success(count);
    }

    @GetMapping("/findById/{id}")
    public CommonResult<Category> findById(@PathVariable("id") Integer id){
        Category category = categoryService.findById(id);
        return CommonResult.success(category);
    }

    @GetMapping("/findAll")
    public CommonResult<List<Category>> findAll(){
        List<Category> list = categoryService.findAll();
        return CommonResult.success(list);
    }

    @GetMapping("/findByParentId/{pid}")
    public CommonResult<List<Category>> findByParentId(@PathVariable("pid") Integer pid){
        List<Category> list = categoryService.findByParentId(pid);
        return CommonResult.success(list);
    }
}
