package com.lmw.web.controller.user;

import com.lmw.common.result.Result;
import com.lmw.web.service.IUserRelationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/relation")
@Tag(name = "用户关系管理接口")
public class UserRelationController {


    @Autowired
    private IUserRelationService userRelationService;
    //关注用户
    @Operation(summary = "关注用户")
    @PostMapping("/follow")
    public Result follow(@RequestParam @Parameter(description = "要关注的用户id") Integer userId){
        userRelationService.follow(userId);
        return Result.ok();
    }

    //取消关注

    //获取关注列表

    //获取粉丝列表
}
