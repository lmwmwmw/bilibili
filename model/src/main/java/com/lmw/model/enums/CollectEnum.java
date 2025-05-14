package com.lmw.model.enums;

// 收藏状态
enum CollectEnum implements BaseEnum {
    NOT_COLLECTED(0, "未收藏"),
    COLLECTED(1, "已收藏");

    private final Integer code;
    private final String name;

    CollectEnum(Integer code, String name) {
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

    public static CollectEnum valueOfCode(Integer code) {
        for (CollectEnum enumValue : values()) {
            if (enumValue.code.equals(code)) {
                return enumValue;
            }
        }
        return null;
    }
}