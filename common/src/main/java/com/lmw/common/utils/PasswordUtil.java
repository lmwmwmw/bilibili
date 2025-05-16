package com.lmw.common.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    public static final BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();


    // 密码加密
    public static String encode(String password){
        return bcryptPasswordEncoder.encode(password);
    }

    //验证密码是否匹配
    public static boolean matches(String password,String encodePassword){
        return bcryptPasswordEncoder.matches(password,encodePassword);
    }


}
