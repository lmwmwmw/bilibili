package com.lmw.web.service;

import com.lmw.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lmw.web.dto.*;
import com.lmw.web.vo.UserProfileVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lmw
 * @since 2025-05-14
 */
public interface IUserService extends IService<User> {


    void sendSmsCode(SmsCodeDTO smsCodeDto);

    String phoneLogin(PhoneLoginDTO phoneLoginDto);

    String passwordLogin(UserPasswordLoginDTO userPasswordLoginDTO);

    void setPassword(PasswordUpdateDTO passwordUpdateDTO);

    void logout();

    UserProfileVO getUserInfo();

    void updateUserInfo(UserUpdateDTO userUpdateDTO);


    void updateUserAvatar(String avatar);

    void updateUserBackground(String background);
}
