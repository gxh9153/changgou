package com.changgou.search.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @Author:gxh
 * @Date: 2021/1/4 15:50
 */
@Data
@Document(indexName = "skuinfo", type = "docs")
public class SkuInfo implements Serializable {


    //@id 表示文档的唯一标识
    @Id
    private Long id;

    /**
     * Text类型，text支持分词
     * index = true ;添加数据的时候，是否分词
     * analyzer 创建索引的分词器
     * store =false 是否存储
     * searchAnalyzer 搜索时使用的分词器
     */
    @Field(type = FieldType.Text,analyzer = "ik_smart",index = true,store = false,searchAnalyzer = "ik_smart")
    private String name;

    //商品价格，单位为：元
    @Field(type = FieldType.Double)
    private Long price;

    //库存数量

    private Integer num;

    //商品图片
    //@Field(index = false)
    private String image;

    //商品状态，1-正常，2-下架，3-删除
    private String status;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

    //是否默认
    private String isDefault;

    //SPUID
    private Long spuId;

    //类目ID
    private Long categoryId;

    //类目名称
    // FieldType.Keyword 是一个关键字 (keyword) 表示不分词.
    @Field(type = FieldType.Keyword)
    private String categoryName;

    //品牌名称
    @Field(type = FieldType.Keyword)
    private String brandName;

    //规格

    private String spec;

    //动态的域的添加和变化

    //规格参数
    @Field(type = FieldType.Object)
    private Map<String, Object> specMap;
}
