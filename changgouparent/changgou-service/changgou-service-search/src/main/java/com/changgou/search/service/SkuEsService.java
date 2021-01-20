package com.changgou.search.service;

import java.util.Map;

/**
 * @Author:gxh
 * @Date: 2021/1/416:52
 */
public interface SkuEsService {

    Map<String,Object> search(Map<String,String> searchMap);
    /**
     * 导入索引库
     */
    void importData();
}
