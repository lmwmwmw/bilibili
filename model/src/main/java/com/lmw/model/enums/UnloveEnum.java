package com.lmw.model.enums;

// 点踩状态
enum UnloveEnum implements BaseEnum {
    NOT_DISLIKED(0, "未点踩"),
    DISLIKED(1, "已点踩");

    private final Integer code;
    private final String name;

    UnloveEnum(Integer code, String name) {
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

    public static UnloveEnum valueOfCode(Integer code) {
        for (UnloveEnum enumValue : values()) {
            if (enumValue.code.equals(code)) {
                return enumValue;
            }
        }
        return null;
    }
}