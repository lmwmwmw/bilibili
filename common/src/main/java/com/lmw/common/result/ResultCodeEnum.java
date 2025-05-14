package com.lmw.common.result;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    SUCCESS(200, "操作成功"),
    FAIL(201, "操作失败"),
    LOGIN_AUTH(305, "未登录"),
    TOKEN_EXPIR(306, "token已过期"),
    //token已过期
    TOKEN_INVAILID(307, "token已过期"),
    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误");

    private final int code;
    private final String message;

    ResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
