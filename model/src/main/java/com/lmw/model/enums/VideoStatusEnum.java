package com.lmw.model.enums;

// 视频状态
public enum VideoStatusEnum implements BaseEnum {
    PENDING(0, "审核中"),
    APPROVED(1, "已过审"),
    REJECTED(2, "未通过"),
    DELETED(3, "已删除");

    private final Integer code;
    private final String name;

    VideoStatusEnum(Integer code, String name) {
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

    public static VideoStatusEnum valueOfCode(Integer code) {
        for (VideoStatusEnum enumValue : values()) {
            if (enumValue.code.equals(code)) {
                return enumValue;
            }
        }
        return null;
    }
}