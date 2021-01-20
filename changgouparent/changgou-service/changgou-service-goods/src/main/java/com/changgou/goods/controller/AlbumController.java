package com.changgou.goods.controller;

import com.changgou.api.CommonResult;
import com.changgou.goods.pojo.Album;
import com.changgou.goods.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    /**
     * 查询所有相册
     * @return
     */
    @GetMapping("/findAll")
    public CommonResult<List<Album>> findAll(){
        List<Album> albumList = albumService.findAll();
        return CommonResult.success(albumList);
    }

    /**
     * 根据id查询相册
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public CommonResult<Album> findById(@PathVariable("id") Long id){
        Album album = albumService.findById(id);
        return CommonResult.success(album);
    }

    /**
     * 创建相册
     * @param album
     * @return
     */
    @PostMapping("/create")
    public CommonResult<Integer> createAlbum(@RequestBody  Album album){
        int count = albumService.createAlbum(album);
        return CommonResult.success(count);
    }

    @PutMapping("/update/{id}")
    /**
     * 更新相册
     * @param album
     * @return
     */
    public CommonResult<Integer> updateAlbum(@PathVariable("id") Long id,@RequestBody Album album){
        album.setId(id);
        int count = albumService.updateAlbum(album);
        return CommonResult.success(count);
    }

    /**
     * 根据id删除相册
     * @param id
     * @return
     */
    @DeleteMapping("deleteById/{id}")
    public CommonResult<Integer> deleteById(@PathVariable("id") Long id){
        int count = albumService.deleteById(id);
        return CommonResult.success(count);
    }

    /**
     * 根据关键词查询相册
     * @param album
     * @return
     */
    @GetMapping("/findByKeyWords")
    public CommonResult<List<Album>> findByKeyWords(@RequestBody Album album){
        List<Album> albumList = albumService.findByKeyWords(album);
        return CommonResult.success(albumList);
    }

    /**
     * 分页查询相册
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findByPage/{pageNum}/{pageSize}")
    public CommonResult<List<Album>> findByPage(@PathVariable("/pageNum") Integer pageNum,
                                                @PathVariable("/pageSize")Integer pageSize){
        List<Album> albumList = albumService.findByPage(pageNum, pageSize);
        return CommonResult.success(albumList);
    }

    /**
     * 根据关键词分页查询相册
     * @param pageNum
     * @param pageSize
     * @param album
     * @return
     */
    @GetMapping("findByKwdAndPage/{pageNum}/{pageSize}")
    public CommonResult<List<Album>> findByKwdAndPage(@PathVariable("pageNum")Integer pageNum,
                                                      @PathVariable("pageSize")Integer pageSize,
                                                      @RequestBody Album album){
        List<Album> albumList = albumService.findByKwAndPage(pageNum, pageSize, album);
        return CommonResult.success(albumList);
    }
}
