package com.changgou.utils;

import com.changgou.entity.Result;

public class ResultVOUtils {
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(0);
        result.setMessage("成功");
        result.setData(object);
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }
}
