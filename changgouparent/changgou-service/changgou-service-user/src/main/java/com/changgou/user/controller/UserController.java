package com.changgou.user.controller;

import com.alibaba.fastjson.JSON;
import com.changgou.api.CommonResult;
import com.changgou.entity.BCrypt;
import com.changgou.entity.JwtUtil;
import com.changgou.user.pojo.User;
import com.changgou.user.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/****
 * @Author:gxh
 * @Date 2021/1/12
 *****/
@Api(value = "UserController")
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;



    @ApiOperation(value = "User条件分页查询",notes = "分页条件查询User方法详情",tags = {"UserController"})
    @GetMapping("/login")
    public CommonResult login(String username, String password, HttpServletResponse response){
        //查询用户信息
        User user = userService.findById(username);
        //数据库密码和输入密码进行对比
        if(BCrypt.checkpw(password,user.getPassword())){
            //创建用户令牌信息
            Map<String,Object> tokenMap = new HashMap<String,Object>();
            tokenMap.put("role","GXH");
            tokenMap.put("success","SUCCESS");
            tokenMap.put("username",username);
            String token = JwtUtil.createJWT(UUID.randomUUID().toString(), JSON.toJSONString(tokenMap), null);
            //把令牌存到cookie
            Cookie cookie = new Cookie("Authentication",token);
            cookie.setDomain("localhost");
            cookie.setPath("/");
            response.addCookie(cookie);
            //把令牌作为参数传给用户
            return CommonResult.success(token,"登录成功！");
        }
        return CommonResult.failed("账号或密码有误！");
    }


    /***
     * User分页条件搜索实现
     * @param user
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "User条件分页查询",notes = "分页条件查询User方法详情",tags = {"UserController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<User>> findPage(@RequestBody(required = false) @ApiParam(name = "User对象",value = "传入JSON数据",required = false) User user, @PathVariable  int page, @PathVariable  int size){
        //调用UserService实现分页条件查询User
        List<User> list = userService.findPage(user, page, size);
        return CommonResult.success(list);
    }

    /***
     * User分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "User分页查询",notes = "分页查询User方法详情",tags = {"UserController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public CommonResult<List<User>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用UserService实现分页查询User
        List<User> list = userService.findPage(page, size);
        return CommonResult.success(list);
    }

    /***
     * 多条件搜索品牌数据
     * @param user
     * @return
     */
    @ApiOperation(value = "User条件查询",notes = "条件查询User方法详情",tags = {"UserController"})
    @PostMapping(value = "/search" )
    public CommonResult<List<User>> findList(@RequestBody(required = false) @ApiParam(name = "User对象",value = "传入JSON数据",required = false) User user){
        //调用UserService实现条件查询User
        List<User> list = userService.findList(user);
        return CommonResult.success(list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "User根据ID删除",notes = "根据ID删除User方法详情",tags = {"UserController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping(value = "/{id}" )
    public CommonResult<Integer> delete(@PathVariable String id){
        //调用UserService实现根据主键删除
        int count = userService.delete(id);
        return CommonResult.success(count);
    }

    /***
     * 修改User数据
     * @param user
     * @param id
     * @return
     */
    @ApiOperation(value = "User根据ID修改",notes = "根据ID修改User方法详情",tags = {"UserController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @PutMapping(value="/{id}")
    public CommonResult<Integer> update(@RequestBody @ApiParam(name = "User对象",value = "传入JSON数据",required = false) User user,@PathVariable String id){
        //设置主键值
        user.setUsername(id);
        //调用UserService实现修改User
        int count = userService.update(user);
        return CommonResult.success(count);
    }

    /***
     * 新增User数据
     * @param user
     * @return
     */
    @ApiOperation(value = "User添加",notes = "添加User方法详情",tags = {"UserController"})
    @PostMapping
    public CommonResult<Integer> add(@RequestBody  @ApiParam(name = "User对象",value = "传入JSON数据",required = true) User user){
        //调用UserService实现添加User
        int count = userService.add(user);
        return CommonResult.success(count);
    }

    /***
     * 根据ID查询User数据
     * @param id
     * @return
     */
    @ApiOperation(value = "User根据ID查询",notes = "根据ID查询User方法详情",tags = {"UserController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @GetMapping("/{id}")
    public CommonResult<User> findById(@PathVariable String id){
        //调用UserService实现根据主键查询User
        User user = userService.findById(id);
        return CommonResult.success(user);
    }

    /***
     * 根据ID查询User数据
     * @param id
     * @return
     */
    @ApiOperation(value = "User根据ID查询",notes = "根据ID查询User方法详情",tags = {"UserController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @GetMapping("/load/{id}")
    public CommonResult<User> findByName(@PathVariable String id){
        //调用UserService实现根据主键查询User
        User user = userService.findById(id);
        return CommonResult.success(user);
    }


    /***
     * 查询User全部数据
     * //只允许admin访问
     * @return
     */
    @ApiOperation(value = "查询所有User",notes = "查询所User有方法详情",tags = {"UserController"})
    @GetMapping
    public CommonResult<List<User>> findAll(){
        //调用UserService实现查询所有User
        List<User> list = userService.findAll();
        return CommonResult.success(list);
    }
}
