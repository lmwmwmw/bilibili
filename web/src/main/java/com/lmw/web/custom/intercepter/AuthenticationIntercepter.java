package com.lmw.web.custom.intercepter;

import com.lmw.common.constant.RedisConstant;
import com.lmw.common.exception.BilibiliException;
import com.lmw.common.login.LoginHolder;
import com.lmw.common.login.LoginUser;
import com.lmw.common.result.ResultCodeEnum;
import com.lmw.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class AuthenticationIntercepter implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        //解析token
        String token = request.getHeader("access-token");
        Claims claims = JwtUtil.parseToken(token);
        Long userId = claims.get("userId", Long.class);
        String userName = claims.get("userName", String.class);

        //验证redis中的token
        String redisKey = RedisConstant.USER_TOKEN_PREFIX+userId;
        String storeToken = stringRedisTemplate.opsForValue().get(redisKey);

        //如果redis中没有token或者token不一致，说明token失效
        if(storeToken==null||!storeToken.equals(token)){
            log.error("token失效");
            throw new BilibiliException(ResultCodeEnum.TOKEN_INVAILID);
        }

        LoginHolder.setLoginUser(new LoginUser(userId, userName));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginHolder.remove();
    }
}
