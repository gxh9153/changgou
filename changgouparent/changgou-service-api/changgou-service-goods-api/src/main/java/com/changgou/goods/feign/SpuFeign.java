package com.changgou.goods.feign;

import com.changgou.api.CommonResult;
import com.changgou.goods.pojo.Spu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:gxh
 * @Date: 2021/1/18 14:16
 */
@FeignClient(value="goods")
@RequestMapping("/spu")
public interface SpuFeign {

    /**
     * 根据id查询spu
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    CommonResult<Spu> findById(@PathVariable Long id);
}
