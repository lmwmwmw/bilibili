package com.lmw.common.constant;

public class RedisConstant {
    public static final String REGISTER_PREFIX = "register:";
    public static final String LOGIN_PREFIX = "login:";
    public static final Integer APP_LOGIN_CODE_RESEND_TIME_SEC = 60*5;
    public static final Integer APP_LOGIN_CODE_TTL_SEC = 60 * 10;

    // 用户 token 前缀
    public static final String USER_TOKEN_PREFIX = "user:token:";

    // token 过期时间（与 JWT 过期时间保持一致，假设是24小时）
    public static final long TOKEN_EXPIRE_TIME = 24 * 60 * 60;
}
