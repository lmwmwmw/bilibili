package com.lmw.common.result;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    SUCCESS(200, "操作成功"),
    FAIL(201, "操作失败"),
    LOGIN_AUTH(305, "未登录"),
    TOKEN_EXPIR(306, "token已过期"),
    //token已过期

    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    SEND_SMS_TOO_OFTEN(504, "验证法发送过于频繁"),

    ACCOUNTEXIST_ERROR(301, "账号已存在"),
    CAPTCHA_CODE_ERROR(302, "验证码错误"),
    CAPTCHA_CODE_EXPIRED(303, "验证码已过期"),
    CAPTCHA__CODE_NOT_FOUND(304, "未输入验证码"),
    TOKEN_INVAILID(307, "token已失效"),
    PHONE_EMPTY(308, "手机号不能为空"),
    USER_BAN(309, "用户被封禁"),
    PASSWORD_NOT_SAME( 310, "密码不一致"),
    OLD_PASSWORD_ERROR( 311, "旧密码错误" ),
    PASSWORD_EMPTY( 312, "密码不能为空" ),
    USER_NOT_EXIST_OR_PASSWORD_NOT_SET( 313, "用户不存在或密码未设置"),
    PASSWORD_ERROR( 314, "密码错误");

    private final int code;
    private final String message;

    ResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
