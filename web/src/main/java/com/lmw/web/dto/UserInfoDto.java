package com.lmw.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserInfoDto {
    @Schema(description = "用户ID")
    private Integer uid;
    
    @Schema(description = "手机号")
    private String phone;
    
    @Schema(description = "用户昵称")
    private String nickname;
    
    @Schema(description = "用户头像URL")
    private String avatar;
    
    @Schema(description = "主页背景图URL")
    private String background;
    
    @Schema(description = "性别 0女 1男 2未知")
    private Integer gender;
    
    @Schema(description = "个性签名")
    private String description;
    
    @Schema(description = "用户状态 0正常 1封禁 2注销")
    private Integer state;
    
    @Schema(description = "用户角色 0普通用户 1管理员 2超级管理员")
    private Integer role;
    
    @Schema(description = "注册时间")
    private LocalDateTime createDate;
    
    @Schema(description = "粉丝数量")
    private Integer fansCount;
    
    @Schema(description = "关注数量")
    private Integer followsCount;
    
    @Schema(description = "视频数量")
    private Integer videosCount;
}