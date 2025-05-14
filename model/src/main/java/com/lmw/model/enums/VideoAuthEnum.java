package com.lmw.model.enums;

// 视频版权声明
enum VideoAuthEnum implements BaseEnum {
    NO_CLAIM(0, "不声明"),
    NO_REPOST(1, "未经允许禁止转载");

    private final Integer code;
    private final String name;

    VideoAuthEnum(Integer code, String name) {
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

    public static VideoAuthEnum valueOfCode(Integer code) {
        for (VideoAuthEnum enumValue : values()) {
            if (enumValue.code.equals(code)) {
                return enumValue;
            }
        }
        return null;
    }
}