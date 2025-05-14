package com.lmw.model.enums;

// 用户状态
enum UserStateEnum implements BaseEnum {
    NORMAL(0, "正常"),
    BANNED(1, "封禁"),
    DEACTIVATED(2, "注销");

    private final Integer code;
    private final String name;

    UserStateEnum(Integer code, String name) {
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

    public static UserStateEnum valueOfCode(Integer code) {
        for (UserStateEnum enumValue : values()) {
            if (enumValue.code.equals(code)) {
                return enumValue;
            }
        }
        return null;
    }
}