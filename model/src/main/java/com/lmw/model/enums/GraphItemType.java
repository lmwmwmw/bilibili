package com.lmw.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GraphItemType implements BaseEnum {

    AVATAR(1, "头像"),

    BACKGROUND(2, "背景图片");


    @EnumValue
    @JsonValue
    private Integer code;
    private String name;

    @Override
    public Integer getCode() {
        return this.code;
    }


    @Override
    public String getName() {
        return name;
    }

    GraphItemType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

}
