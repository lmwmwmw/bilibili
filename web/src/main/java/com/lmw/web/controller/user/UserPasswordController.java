package com.lmw.web.controller;

import com.lmw.common.result.Result;
import com.lmw.web.dto.PasswordUpdateDTO;
import com.lmw.web.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/password")
@Tag(name = "用户密码管理接口")
public class UserPasswordController {

    @Autowired
    private IUserService userService;

    @Operation(summary = "设置/修改密码")
    @PostMapping("/password/set")
    public Result setPassword(@RequestBody PasswordUpdateDTO passwordUpdateDTO){
        userService.setPassword(passwordUpdateDTO);
        return Result.ok();
    }


}
