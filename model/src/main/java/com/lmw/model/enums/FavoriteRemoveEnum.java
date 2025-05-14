package com.lmw.model.enums;

// 收藏移除状态
enum FavoriteRemoveEnum implements BaseEnum {
    NOT_REMOVED(null, "未移除"),
    REMOVED(1, "已移除");

    private final Integer code;
    private final String name;

    FavoriteRemoveEnum(Integer code, String name) {
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

    public static FavoriteRemoveEnum valueOfCode(Integer code) {
        for (FavoriteRemoveEnum enumValue : values()) {
            if (enumValue.code == null && code == null) {
                return enumValue;
            }
            if (enumValue.code != null && enumValue.code.equals(code)) {
                return enumValue;
            }
        }
        return null;
    }
}