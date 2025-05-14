package com.lmw.web.controller;


import com.lmw.common.result.Result;
import com.lmw.web.dto.*;
import com.lmw.web.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author lmw
 * @since 2025-05-14
 */
@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    private IUserService userService;


    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterDto userRegisterDTO) {
        userService.register(userRegisterDTO);
        return Result.ok();
    }

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserLoginDto userLoginDTO) {
        return Result.ok();
    }

    @Operation(summary = "获取用户信息")
    @GetMapping("/info")
    public Result<UserInfoDto> getUserInfo() {
        return Result.ok();
    }

    @Operation(summary = "获取图形验证码")
    @GetMapping("login/captcha")
    public Result<CaptchaDto> getCaptcha() {
        CaptchaDto result = userService.getCaptcha();
        return Result.ok(result);
    }

    @Operation(summary = "更新用户信息")
    @PutMapping("/info")
    public Result updateUserInfo(@RequestBody UserInfoDto userInfoDTO) {
        return Result.ok();
    }

    // 修改密码
    @Operation(summary = "修改密码")
    @PutMapping("/password")
    public Result updatePassword(@RequestBody PasswordUpdateDto passwordUpdateDTO) {
        return Result.ok();
    }


    // 上传头像
    @Operation(summary = "上传头像")
    @PostMapping("/avatar")
    public Result uploadAvatar(GraphUpdateDto avatarUpdateDTO) {
        return Result.ok();
    }

    //上传背景图片
    @Operation(summary = "上传背景图片")
    @PostMapping("/background")
    public Result uploadBackground(GraphUpdateDto graphUpdateDto) {
        return Result.ok();
    }

}
