package com.lmw.model.enums;

enum GenderEnum implements BaseEnum {
    FEMALE(0, "女"),
    MALE(1, "男"),
    UNKNOWN(2, "未知");

    private final Integer code;
    private final String name;

    GenderEnum(Integer code, String name) {
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

    public static GenderEnum valueOfCode(Integer code) {
        for (GenderEnum enumValue : values()) {
            if (enumValue.code.equals(code)) {
                return enumValue;
            }
        }
        return null;
    }
}
