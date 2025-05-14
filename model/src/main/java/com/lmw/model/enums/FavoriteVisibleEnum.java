package com.lmw.model.enums;

// 收藏夹可见性
public enum FavoriteVisibleEnum implements BaseEnum {
    HIDDEN(0, "隐藏"),
    PUBLIC(1, "公开");

    private final Integer code;
    private final String name;

    FavoriteVisibleEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    public static FavoriteVisibleEnum valueOfCode(Integer code) {
        for (FavoriteVisibleEnum enumValue : values()) {
            if (enumValue.code.equals(code)) {
                return enumValue;
            }
        }
        return null;
    }
}