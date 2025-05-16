package com.lmw.web.custom.config;


import com.lmw.web.custom.converter.BaseEnumConverterFactory;
import com.lmw.web.custom.intercepter.AuthenticationIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private AuthenticationIntercepter authenticationIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationIntercepter).addPathPatterns("/api/**").excludePathPatterns(
                "/api/user/auth/phone/login",   // 手机登录
                "/api/user/auth/password/login",// 密码登录
                "/api/user/auth/login/sendsmscode" ); // 发送验证码)
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new BaseEnumConverterFactory());
    }
}
