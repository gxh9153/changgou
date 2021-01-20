package com.changgou.enums;

public enum  GoodsStatusEnum {

    SHELVES("0","已上架"),

    UNDERCARRIAGE("1","已下架"),
    ;

    private String value;

    private String name;

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    GoodsStatusEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }
}
