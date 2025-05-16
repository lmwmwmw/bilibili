package com.lmw.web.controller.user;


import com.lmw.common.result.Result;
import com.lmw.web.dto.*;
import com.lmw.web.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/api/user/auth")
@Tag(name = "用户登录管理接口")
public class UserAuthController {


    @Autowired
    private IUserService userService;



    // 添加手机验证码登录/注册的API
    @Operation(summary = "手机验证码登录/注册")
    @PostMapping("/phone/login")
    public Result<String> phoneLogin(@RequestBody PhoneLoginDTO phoneLoginDto) {
        String token = userService.phoneLogin(phoneLoginDto);
        return Result.ok(token);
    }

    @Operation(summary = "发送sms验证码")
    @GetMapping("login/sendsmscode")
    public Result sendSmsCode(SmsCodeDTO smsCodeDto) {
        userService.sendSmsCode(smsCodeDto);
        return Result.ok();
    }

    @Operation(summary = "密码登陆")
    @PostMapping("/password/login")
    public Result<String> passwordLogin(@RequestBody UserPasswordLoginDTO userPasswordLoginDTO){
       String token =  userService.passwordLogin(userPasswordLoginDTO);
        return Result.ok(token);
    }


    @Operation(summary = "退出登录")
    @PostMapping("/logout")
    public Result logout() {
        userService.logout();
        return Result.ok();
    }


}
