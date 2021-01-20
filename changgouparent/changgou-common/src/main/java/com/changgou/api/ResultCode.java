package com.changgou.api;

/**
 * 枚举了一些常用API操作码
 * Created by gxh on 2019/4/19.
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(true,200, "操作成功"),
    FAILED(false,500, "操作失败"),
    VALIDATE_FAILED(false,404, "参数检验失败"),
    UNAUTHORIZED(false,401, "暂未登录或token已经过期"),
    FORBIDDEN(false,403, "没有相关权限");
    private boolean flag;
    private long code;
    private String message;

    private ResultCode(boolean flag,long code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public boolean getFlag(){
        return flag;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
