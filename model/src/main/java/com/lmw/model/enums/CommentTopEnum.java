package com.lmw.model.enums;

// 评论置顶
enum CommentTopEnum implements BaseEnum {
    NORMAL(0, "普通"),
    TOP(1, "置顶");

    private final Integer code;
    private final String name;

    CommentTopEnum(Integer code, String name) {
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

    public static CommentTopEnum valueOfCode(Integer code) {
        for (CommentTopEnum enumValue : values()) {
            if (enumValue.code.equals(code)) {
                return enumValue;
            }
        }
        return null;
    }
}