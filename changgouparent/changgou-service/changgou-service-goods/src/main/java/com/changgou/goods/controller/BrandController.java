package com.changgou.goods.controller;


import com.changgou.api.CommonPage;
import com.changgou.api.CommonResult;
import com.changgou.entity.Result;
import com.changgou.exception.ApiException;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.changgou.utils.ResultVOUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "BrandController", description = "商品品牌管理")
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 查询所有商品数据
     * @return
     */
    @ApiOperation("查询所有品牌")
    @GetMapping("/findAll")
    public CommonResult<List<Brand>> findAll(){
        List<Brand> brandList = brandService.findAll();
        return CommonResult.success(brandList);
    }

    /*@GetMapping("/findAll")
    public Result<Brand> findAll(){
        List<Brand> brandList = brandService.findAll();
        return new Result<Brand>(true, StatusCode.OK,"查询成功",brandList);
    }
*/
    /**
     * 根据id查找品牌
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public CommonResult<Brand>findById(@PathVariable("id") Integer id){
        Brand brand = brandService.findById(id);
        return CommonResult.success(brand);
    }

    /**
     * 新增品牌
     * @param brand
     * @return
     */
    @PostMapping("/create")
    public CommonResult<Integer> create(@RequestBody Brand brand){
        int count = brandService.createBrand(brand);
        return CommonResult.success(count);
    }

    /**
     * 根据id修改品牌
     * @param id
     * @param brand
     * @return
     */
    @PutMapping("/update/{id}")
    public CommonResult<Integer> update(@PathVariable("id") Integer id,@RequestBody Brand brand){
        brand.setId(id);
        int count = brandService.updateBrand(brand);
        return CommonResult.success(count);
    }

    /**
     * 根据id删除品牌
     * @param id
     * @return
     */
    @DeleteMapping("/deleteById/{id}")
    public CommonResult deleteById(@PathVariable("id") Integer id){
        int count = brandService.deleteById(id);
        return CommonResult.success(count);
    }

    /**
     * 根据条件查询品牌
     * @param brand
     * @return
     */
    @GetMapping("/findBrandByKeywords")
    public CommonResult<List<Brand>> findBrandByKeywords(@RequestBody Brand brand){
        List<Brand> brandList = brandService.findByKeyWords(brand);
        return CommonResult.success(brandList);
    }

    /**
     * 分页查询品牌
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findByPage/{pageNum}/{pageSize}")
    public CommonResult<CommonPage<Brand>> findByPage(@PathVariable("pageNum")Integer pageNum,
                                        @PathVariable("pageSize") Integer pageSize){
        List<Brand> brandList = brandService.findByPage(pageNum, pageSize);
        return  CommonResult.success(CommonPage.restPage(brandList));
    }

    /**
     * 根据条件分页查询
     * @param pageNum
     * @param pageSize
     * @param brand
     * @return
     */
    @GetMapping("/findByKwAndPage/{pageNum}/{pageSize}")
    public CommonResult<CommonPage<Brand>> findByKwAndPage(@PathVariable("pageNum")Integer pageNum,
                                         @PathVariable("pageSize") Integer pageSize,
                                         @RequestBody Brand brand){
        List<Brand> brandList = brandService.findByKwAndPage(pageNum, pageSize, brand);
        return CommonResult.success(CommonPage.restPage(brandList));
    }

    @GetMapping("/findByCategoryId/{categoryId}")
    public CommonResult<List<Brand>> findByCategoryId(@PathVariable("categoryId") Integer categoryId){
        List<Brand> list = brandService.findByCategory(categoryId);
        return CommonResult.success(list);
    }
}
