package com.changgou.goods.dto;

import com.changgou.goods.pojo.Sku;
import com.changgou.goods.pojo.Spu;
import lombok.Data;

import java.util.List;

@Data
public class Goods {

    private Spu spu;

    private List<Sku> skuList;
}
