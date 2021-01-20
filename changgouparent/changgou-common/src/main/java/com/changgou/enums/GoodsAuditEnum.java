package com.changgou.enums;

public enum GoodsAuditEnum {

    NOT_AUDIT("0","未审核"),

    APPROVED("1","审核通过"),

    NOT_APPROVED("2","审核不通过")
    ;

    private String value;

    private String name;

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    GoodsAuditEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }
}
