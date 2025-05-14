package com.lmw.model.enums;

// 用户角色
public enum UserRoleEnum implements BaseEnum {
    USER(0, "普通用户"),
    ADMIN(1, "管理员"),
    SUPER_ADMIN(2, "超级管理员");

    private final Integer code;
    private final String name;

    UserRoleEnum(Integer code, String name) {
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

    public static UserRoleEnum valueOfCode(Integer code) {
        for (UserRoleEnum enumValue : values()) {
            if (enumValue.code.equals(code)) {
                return enumValue;
            }
        }
        return null;
    }
}