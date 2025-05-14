package com.lmw.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author lmw
 * @since 2025-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user")
@Schema(description = "用户表")
public class User extends BaseEntity {

    @Schema(description = "用户ID")
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    @Schema(description = "用户账号")
    private String phone;

    @Schema(description = "用户密码")
    private String password;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "用户头像url")
    private String avatar;

    @Schema(description = "主页背景图url")
    private String background;

    @Schema(description = "性别 0女 1男 2未知")
    private Integer gender;

    @Schema(description = "个性签名")
    private String description;

    @Schema(description = "状态 0正常 1封禁 2注销")
    private Integer state;

    @Schema(description = "角色类型 0普通用户 1管理员 2超级管理员")
    private Integer role;

    @Schema(description = "注销时间")
    private LocalDateTime deleteDate;
}
