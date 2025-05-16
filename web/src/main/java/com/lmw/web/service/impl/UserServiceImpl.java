package com.lmw.web.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lmw.common.constant.RedisConstant;
import com.lmw.common.exception.BilibiliException;
import com.lmw.common.login.LoginHolder;
import com.lmw.common.login.LoginUser;
import com.lmw.common.result.ResultCodeEnum;
import com.lmw.common.utils.JwtUtil;
import com.lmw.common.utils.PasswordUtil;
import com.lmw.common.utils.VertifyCodeUtil;
import com.lmw.model.entity.User;
import com.lmw.web.dto.*;
import com.lmw.web.mapper.UserMapper;
import com.lmw.web.service.FileService;
import com.lmw.web.service.ISmsService;
import com.lmw.web.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmw.web.vo.UserProfileVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lmw
 * @since 2025-05-14
 */

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ISmsService smsService;

    @Autowired
    private FileService fileUploadService;

    // 发送短信验证码
    @Override
    public void sendSmsCode(SmsCodeDTO smsCodeDto) {
        //1.生成随机六位验证码
        String code = VertifyCodeUtil.getVertifyCode(6);

        //2.设置redis key
        String type = smsCodeDto.getType();
        String key;
        if ("login".equals(type)) {
            key = RedisConstant.LOGIN_PREFIX + smsCodeDto.getPhone();
        } else {
            key = RedisConstant.REGISTER_PREFIX + smsCodeDto.getPhone();
        }
        //3.调用阿里云服务发送短信验证码,限制一分钟发送一次
        Boolean hasKey = stringRedisTemplate.hasKey(key);
        if (hasKey) {
            Long ttl = stringRedisTemplate.getExpire(key);
            if (RedisConstant.APP_LOGIN_CODE_RESEND_TIME_SEC - ttl < 60) {
                log.warn("用户{}在{}秒内已经发送过验证码了", smsCodeDto.getPhone(), ttl);
                throw new BilibiliException(ResultCodeEnum.SEND_SMS_TOO_OFTEN);
            }
        }

        //4.存入Redis中,验证码五分钟有效
        stringRedisTemplate.opsForValue().set(key, code, RedisConstant.APP_LOGIN_CODE_RESEND_TIME_SEC, TimeUnit.SECONDS);
        smsService.sendcode(smsCodeDto.getPhone(), code);
        log.info("发送验证码成功");
    }


    //手机号登陆
    @Override
    public String phoneLogin(PhoneLoginDTO phoneLoginDto) {
        //1.判断手机号是否为空
        if (phoneLoginDto.getPhone() == null) {
            log.error("手机号不能为空");
            throw new BilibiliException(ResultCodeEnum.PHONE_EMPTY);
        }

        //2.判断验证码是否为空
        if (phoneLoginDto.getCode() == null) {
            log.error("验证码不能为空");
            throw new BilibiliException(ResultCodeEnum.CAPTCHA__CODE_NOT_FOUND);
        }
        //3.查询用户是否存在
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getPhone, phoneLoginDto.getPhone());
        User user = userMapper.selectOne(userLambdaQueryWrapper);
        //4.若用户不存在,则创建用户
        if (user == null) {
            user = new User();
            user.setPhone(phoneLoginDto.getPhone());
            user.setNickname("用户" + phoneLoginDto.getPhone().substring(7, 11));
            user.setState(0);
            userMapper.insert(user);
        }
        //5.若用户存在,判断用户封禁状态
        if (user.getState() == 2) {
            log.error("用户被封禁,用户ID:{}", user.getUid());
            throw new BilibiliException(ResultCodeEnum.USER_BAN);
        }
        //6.删除redis验证码
        stringRedisTemplate.delete(RedisConstant.LOGIN_PREFIX + phoneLoginDto.getPhone());
        //7.生成新token
        String token = JwtUtil.createToken(Long.valueOf(user.getUid()), user.getPhone());
        //8.将token存入redis
        String redisKey = RedisConstant.USER_TOKEN_PREFIX + user.getUid();
        //9.查询该用户是否已经登陆
        String oldtoken = stringRedisTemplate.opsForValue().get(redisKey);
        //10.若用户已经登陆,则删除redis中的token,也可通知其他设备将其踢出
        if (oldtoken != null) {
            stringRedisTemplate.delete(redisKey);
        }
        //11.将token存入redis中
        stringRedisTemplate.opsForValue().set(redisKey, token, RedisConstant.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        log.info("用户{}登陆成功,token:{}", phoneLoginDto.getPhone(), token);
        return token;
    }


    //密码登陆
    @Override
    public String passwordLogin(UserPasswordLoginDTO userPasswordLoginDTO) {
        // 1. 参数校验
        if (userPasswordLoginDTO.getPhone() == null) {
            log.warn("用户手机号不能为空");
            throw new BilibiliException(ResultCodeEnum.PHONE_EMPTY);
        }
        if (userPasswordLoginDTO.getPassword() == null) {
            log.warn("用户密码不能为空");
            throw new BilibiliException(ResultCodeEnum.PASSWORD_EMPTY);
        }

        //2.查询用户是否存在
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getPhone, userPasswordLoginDTO.getPhone());
        userLambdaQueryWrapper.select(User::getUid, User::getPassword, User::getState);
        User user = userMapper.selectOne(userLambdaQueryWrapper);
        if (user == null) {
            log.error("用户不存在");
            throw new BilibiliException(ResultCodeEnum.USER_NOT_EXIST_OR_PASSWORD_NOT_SET);
        }
        //3.校验密码
        if (!PasswordUtil.matches(userPasswordLoginDTO.getPassword(), user.getPassword())) {
            log.error("密码错误");
            throw new BilibiliException(ResultCodeEnum.PASSWORD_ERROR);
        }

        //4.校验是否封禁
        if (user.getState() == 2) {
            log.error("用户被封禁");
            throw new BilibiliException(ResultCodeEnum.USER_BAN);
        }
        //7.生成新token
        String token = JwtUtil.createToken(Long.valueOf(user.getUid()), user.getPhone());
        //8.将token存入redis
        String redisKey = RedisConstant.USER_TOKEN_PREFIX + user.getUid();
        //9.查询该用户是否已经登陆
        String oldtoken = stringRedisTemplate.opsForValue().get(redisKey);
        //10.若用户已经登陆,则删除redis中的token,也可通知其他设备将其踢出
        if (oldtoken != null) {
            stringRedisTemplate.delete(redisKey);
        }
        //11.将token存入redis中
        stringRedisTemplate.opsForValue().set(redisKey, token, RedisConstant.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        log.info("用户登录成功");
        return token;
    }


    //设置更新密码
    @Override
    public void setPassword(PasswordUpdateDTO passwordUpdateDTO) {
        // 1.接收并校验输入参数
        if (!passwordUpdateDTO.getNewPassword().equals(passwordUpdateDTO.getConfirmPassword())) {
            log.error("[用户服务]：新密码与确认密码不一致");
            throw new BilibiliException(ResultCodeEnum.PASSWORD_NOT_SAME);
        }
        //2.从线程中获取用户信息
        LoginUser loginUser = LoginHolder.getLoginUser();
        String phone = loginUser.getUserName();
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getPhone, phone);
        User user = userMapper.selectOne(userLambdaQueryWrapper);
        // 3.如果用户已经设置过密码，需要验证旧密码
        if (user.getPassword() != null) {
            if (!PasswordUtil.matches(passwordUpdateDTO.getOldPassword(), user.getPassword())) {
                log.error("旧密码错误");
                throw new BilibiliException(ResultCodeEnum.OLD_PASSWORD_ERROR);
            }
        }

        //4.更新使用BCrypt加密新密码
        LambdaUpdateWrapper<User> userLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        userLambdaUpdateWrapper.eq(User::getPhone, phone)
                .set(User::getPassword, PasswordUtil.encode(passwordUpdateDTO.getNewPassword()));
        userMapper.update(null, userLambdaUpdateWrapper);
        log.info("用户{}修改密码成功", phone);
    }


    //退出登陆
    @Override
    public void logout() {
        LoginUser loginUser = LoginHolder.getLoginUser();
        if (loginUser != null) {
            stringRedisTemplate.delete(RedisConstant.USER_TOKEN_PREFIX + loginUser.getUserId());
        }
        log.info("用户{}退出登录成功", loginUser.getUserName());
    }


    //获取用户信息
    @Override
    public UserProfileVO getUserInfo() {
        //获取当先登陆用户
        LoginUser loginUser = LoginHolder.getLoginUser();
        if (loginUser == null) {
            log.error("用户未登录");
            throw new BilibiliException(ResultCodeEnum.LOGIN_AUTH);
        }
        Long userId = loginUser.getUserId();
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUid, userId);
        User user = userMapper.selectOne(userLambdaQueryWrapper);
        UserProfileVO userProfileVO = new UserProfileVO();
        BeanUtils.copyProperties(user, userProfileVO);
        return userProfileVO;
    }


    //更新用户基本信息
    @Override
    public void updateUserInfo(UserUpdateDTO userUpdateDTO) {
        LoginUser loginUser = LoginHolder.getLoginUser();
        Long userId = loginUser.getUserId();
        if(userId == null){
            log.error("[更新用户信息] 用户未登录");
            throw new BilibiliException(ResultCodeEnum.LOGIN_AUTH);
        }
        LambdaUpdateWrapper<User> userLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        userLambdaUpdateWrapper.eq(User::getUid, userId)
                .set(User::getNickname, userUpdateDTO.getNickname())
                .set(User::getDescription, userUpdateDTO.getDescription())
                .set(User::getGender, userUpdateDTO.getGender());
        super.update(userLambdaUpdateWrapper);
    }


    //更新头像
    @Override
    public void updateUserAvatar(String avatar) {
        LoginUser loginUser = LoginHolder.getLoginUser();
        Long userId = loginUser.getUserId();

        //获取当前用户头像
        User user = userMapper.selectById(userId);
        String oldAvatar = user.getAvatar();
        //如果有旧头像，则删除
        if (oldAvatar != null ) {
            fileUploadService.deleteByUrl(oldAvatar);
        }
        //更新头像
        LambdaUpdateWrapper<User> userLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        userLambdaUpdateWrapper.eq(User::getUid, userId);
        userLambdaUpdateWrapper.set(User::getAvatar, avatar);
        this.update(userLambdaUpdateWrapper);
        log.info("更新用户头像成功");
    }

    @Override
    public void updateUserBackground(String background) {
        LoginUser loginUser = LoginHolder.getLoginUser();
        Long userId = loginUser.getUserId();
        User user = userMapper.selectById(userId);
        String oldBackground = user.getBackground();
        //判断是否有旧背景
        if (oldBackground != null ) {
            fileUploadService.deleteByUrl(oldBackground);
        }
        //更新背景
        LambdaUpdateWrapper<User> userLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        userLambdaUpdateWrapper.eq(User::getUid, userId)
                .set(User::getBackground, background);
        super.update(userLambdaUpdateWrapper);
        log.info("更新用户背景成功");
    }


}
