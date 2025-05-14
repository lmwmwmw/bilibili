package com.lmw.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lmw.common.constant.RedisConstant;
import com.lmw.common.exception.BilibiliException;
import com.lmw.common.result.ResultCodeEnum;
import com.lmw.model.entity.User;
import com.lmw.model.enums.UserStateEnum;
import com.lmw.web.dto.CaptchaDto;
import com.lmw.web.dto.UserRegisterDto;
import com.lmw.web.mapper.UserMapper;
import com.lmw.web.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wf.captcha.SpecCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lmw
 * @since 2025-05-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(UserRegisterDto userRegisterDTO) {
        //1.判断验证码是否为空
        if (userRegisterDTO.getCaptchacode() == null) {
            throw new BilibiliException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_NOT_FOUND);
        }
        //2.判断验证码是否过期
        String rediscode = stringRedisTemplate.opsForValue().get(userRegisterDTO.getCaptchakey());
        if (rediscode == null){
            throw new BilibiliException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_EXPIRED);
        }
        //3.判断验证码是否正确
        if(!rediscode.equals(userRegisterDTO.getCaptchacode())){
            throw new BilibiliException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR);
        }

        //4.判断手机号是否重复
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhone, userRegisterDTO.getPhone());
        if (this.count(queryWrapper) > 0) {
            throw new BilibiliException(ResultCodeEnum.ADMIN_ACCOUNT_EXIST_ERROR);
        }

        //5.保存用户
        User user = new User();
        user.setPhone(userRegisterDTO.getPhone());
        user.setPassword(userRegisterDTO.getPassword());
        user.setNickname(userRegisterDTO.getNickname());
        user.setState(UserStateEnum.NORMAL.getCode());
        user.setGender(userRegisterDTO.getGender());
        userMapper.insert(user);


    }

    @Override
    public CaptchaDto getCaptcha() {
        //1.创建图形验证码
        SpecCaptcha specCaptcha = new SpecCaptcha();
        //2.获取验证码key
        String code = specCaptcha.text().toLowerCase();
        //3.设置uuid
        String key = RedisConstant.REGISTER_PREFIX + UUID.randomUUID().toString();

        //4.保存到redis中,存货周期为60s
        stringRedisTemplate.opsForValue().set(key, code, RedisConstant.APP_LOGIN_CODE_RESEND_TIME_SEC, TimeUnit.SECONDS);
        //5.返回图形验证码
        return new CaptchaDto(specCaptcha.toBase64(), key);
    }
}
