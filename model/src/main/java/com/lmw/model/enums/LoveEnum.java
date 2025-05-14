package com.lmw.model.enums;

// 点赞状态
public enum LoveEnum implements BaseEnum {
    NOT_LIKED(0, "未点赞"),
    LIKED(1, "已点赞");

    private final Integer code;
    private final String name;

    LoveEnum(Integer code, String name) {
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

    public static LoveEnum valueOfCode(Integer code) {
        for (LoveEnum enumValue : values()) {
            if (enumValue.code.equals(code)) {
                return enumValue;
            }
        }
        return null;
    }
}