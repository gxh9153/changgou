package com.changgou.goods.service;

import com.changgou.goods.pojo.Album;

import java.util.List;

public interface AlbumService {
    /**
     * 查询所有相册
     * @return
     */
    List<Album> findAll();

    /**
     * 根据id查询相册
     * @param id
     * @return
     */
    Album findById(Long id);

    /**
     * 添加相册
     * @param album
     */
    int createAlbum(Album album);

    /**
     * 根据id修改相册
     * @param album
     * @return
     */
    int updateAlbum(Album album);

    /**
     * 根据id删除相册
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据条件查询相册
     * @param brand
     * @return
     */
    List<Album> findByKeyWords(Album brand);

    /**
     * 分页查询相册
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Album> findByPage(Integer pageNum, Integer pageSize);

    /**
     * 根据条件分页查询相册
     * @param pageNum
     * @param pageSize
     * @param album
     * @return
     */
    List<Album> findByKwAndPage(Integer pageNum, Integer pageSize,Album album);
}
