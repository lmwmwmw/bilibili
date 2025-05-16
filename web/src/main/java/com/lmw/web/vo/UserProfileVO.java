package com.lmw.web.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "用户资料信息")
public class UserProfileVO {
    
    @Schema(description = "用户ID")
    private Long uid;
    
    @Schema(description = "昵称")
    private String nickname;
    
    @Schema(description = "头像URL")
    private String avatar;
    
    @Schema(description = "个人空间背景图URL")
    private String background;
    
    @Schema(description = "性别 0-未知 1-男 2-女")
    private Integer gender;
    
    @Schema(description = "个人简介")
    private String description;
    
    @Schema(description = "账号状态 0-正常 1-受限 2-封禁")
    private Integer state;
    
    @Schema(description = "用户角色 0-普通用户 1-管理员")
    private Integer role;
    
    @Schema(description = "关注数")
    private Integer followingCount;
    
    @Schema(description = "粉丝数")
    private Integer followerCount;
    
    @Schema(description = "获赞数")
    private Integer likeCount;
    
    @Schema(description = "投稿视频数")
    private Integer videoCount;
}