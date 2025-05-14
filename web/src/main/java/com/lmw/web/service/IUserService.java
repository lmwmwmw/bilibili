package com.lmw.web.service;

import com.lmw.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lmw.web.dto.CaptchaDto;
import com.lmw.web.dto.UserRegisterDto;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lmw
 * @since 2025-05-14
 */
public interface IUserService extends IService<User> {

    void register(UserRegisterDto userRegisterDTO);

    CaptchaDto getCaptcha();
}
