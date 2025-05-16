package com.atguigu.lease.common.utils;

import java.util.Random;

public class VertifyCodeUtil {
    public static String getVertifyCode(int length) {
        StringBuffer code = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length ; i++) {
            code.append(random.nextInt(10));

        }
        return code.toString();
    }

}
