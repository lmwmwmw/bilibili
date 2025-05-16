package com.lmw.web.controller;

import com.lmw.common.result.Result;
import com.lmw.web.dto.UserUpdateDTO;
import com.lmw.web.service.IUserService;
import com.lmw.web.vo.UserProfileVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/profile")
@Tag(name = "用户信息管理接口")
public class UserProfileController {

    @Autowired
    private IUserService userService;

    //获取用户基本信息
    @Operation(summary = "获取用户基本信息")
    @GetMapping("getinfo")
    public Result<UserProfileVO> getUserInfo(){
        UserProfileVO result = userService.getUserInfo();
        return Result.ok(result);
    }
    //更新用户基本信息
    @Operation(summary = "更新用户基本信息")
    @PutMapping("update")
    public Result updateUserInfo(@RequestBody UserUpdateDTO userUpdateDTO){
        userService.updateUserInfo(userUpdateDTO);
        return Result.ok();
    }
    //更新用户头像
    @Operation(summary = "更新用户头像")
    @PutMapping("avatar")
    public Result updateUserAvatar(@RequestParam String avatar){
        userService.updateUserAvatar(avatar);
        return Result.ok();
    }
    //更新用户背景图
    @Operation(summary = "更新用户背景图")
    @PutMapping("background")
    public Result updateUserBackground(@RequestParam String background){
        userService.updateUserBackground(background);
        return Result.ok();
    }
}
