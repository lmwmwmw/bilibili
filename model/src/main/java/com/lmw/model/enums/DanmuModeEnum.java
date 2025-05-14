package com.lmw.model.enums;

// 弹幕模式
enum DanmuModeEnum implements BaseEnum {
    SCROLL(1, "滚动"),
    TOP(2, "顶部"),
    BOTTOM(3, "底部");

    private final Integer code;
    private final String name;

    DanmuModeEnum(Integer code, String name) {
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

    public static DanmuModeEnum valueOfCode(Integer code) {
        for (DanmuModeEnum enumValue : values()) {
            if (enumValue.code.equals(code)) {
                return enumValue;
            }
        }
        return null;
    }
}