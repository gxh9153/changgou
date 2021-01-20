package com.changgou.goods.feign;

import com.changgou.api.CommonResult;
import com.changgou.goods.pojo.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author:gxh
 * @Date: 2021/1/4 16:14
 */
@FeignClient(value = "goods")
@RequestMapping("/sku")
public interface SkuFeign {

    /**
     * 查询sku的全部数据
     * @return
     */
    @GetMapping
    CommonResult<List<Sku>> findAll();

    /**
     * 根据id查询sku
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    CommonResult<Sku> findById(@PathVariable Long id);
}
