package com.lmw.model.enums;

// 收藏夹类型
public enum FavoriteTypeEnum implements BaseEnum {
    DEFAULT(1, "默认收藏夹"),
    CUSTOM(2, "用户创建");

    private final Integer code;
    private final String name;

    FavoriteTypeEnum(Integer code, String name) {
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

    public static FavoriteTypeEnum valueOfCode(Integer code) {
        for (FavoriteTypeEnum enumValue : values()) {
            if (enumValue.code.equals(code)) {
                return enumValue;
            }
        }
        return null;
    }
}