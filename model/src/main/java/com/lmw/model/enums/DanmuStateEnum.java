package com.lmw.model.enums;

// 弹幕状态
public enum DanmuStateEnum implements BaseEnum {
    APPROVED(1, "默认过审"),
    UNDER_REVIEW(2, "被举报审核中"),
    DELETED(3, "删除");

    private final Integer code;
    private final String name;

    DanmuStateEnum(Integer code, String name) {
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

    public static DanmuStateEnum valueOfCode(Integer code) {
        for (DanmuStateEnum enumValue : values()) {
            if (enumValue.code.equals(code)) {
                return enumValue;
            }
        }
        return null;
    }
}