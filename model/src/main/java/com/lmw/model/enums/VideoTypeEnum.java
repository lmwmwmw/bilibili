package com.lmw.model.enums;

// 视频类型
public enum VideoTypeEnum implements BaseEnum {
    ORIGINAL(1, "自制"),
    REPOST(2, "转载");

    private final Integer code;
    private final String name;

    VideoTypeEnum(Integer code, String name) {
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

    public static VideoTypeEnum valueOfCode(Integer code) {
        for (VideoTypeEnum enumValue : values()) {
            if (enumValue.code.equals(code)) {
                return enumValue;
            }
        }
        return null;
    }
}