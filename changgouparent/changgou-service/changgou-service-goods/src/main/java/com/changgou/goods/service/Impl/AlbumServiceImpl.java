package com.changgou.goods.service.Impl;

import com.changgou.goods.dao.AlbumMapper;
import com.changgou.goods.pojo.Album;
import com.changgou.goods.service.AlbumService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    /**
     * 查询所有相册
     * @return
     */
    @Override
    public List<Album> findAll() {
        List<Album> albumList = albumMapper.selectAll();
        return albumList;
    }

    /**
     * 根据id查询相册
     * @param id
     * @return
     */
    @Override
    public Album findById(Long id) {
        Album album = albumMapper.selectByPrimaryKey(id);
        return album;
    }

    /**
     * 创建相册
     * @param album
     * @return
     */
    @Override
    public int createAlbum(Album album) {
        int count = albumMapper.insertSelective(album);
        return count;
    }

    /**
     * 更新相册
     * @param album
     * @return
     */
    @Override
    public int updateAlbum(Album album) {
        int count = albumMapper.updateByPrimaryKeySelective(album);
        return count;
    }

    /**
     * 根据id删除相册
     * @param id
     * @return
     */
    @Override
    public int deleteById(Long id) {
        int count = albumMapper.deleteByPrimaryKey(id);
        return count;
    }

    /**
     * 根据关键词查询相册
     * @param album
     * @return
     */
    @Override
    public List<Album> findByKeyWords(Album album) {
        Example example = createExample(album);
        List<Album> albums = albumMapper.selectByExample(example);
        return albums;
    }

    /**
     * 分页查询相册
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<Album> findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Album> albumList = albumMapper.selectAll();
        return albumList;
    }

    /**
     * 根据关键词分页查询相册
     * @param pageNum
     * @param pageSize
     * @param album
     * @return
     */
    @Override
    public List<Album> findByKwAndPage(Integer pageNum, Integer pageSize, Album album) {
        PageHelper.startPage(pageNum,pageSize);
        Example example = createExample(album);
        List<Album> albumList = albumMapper.selectByExample(example);
        return albumList;
    }

    /**
     * 构建查询对象
     *
     * @param album
     * @return
     */
    public Example createExample(Album album) {
        Example example = new Example(Album.class);
        Example.Criteria criteria = example.createCriteria();
        if (album != null) {
            // 相册名称
            if (!StringUtils.isEmpty(album.getTitle())) {
                criteria.andLike("name", "%" + album.getTitle() + "%");
            }
        }
        return example;
    }
}
