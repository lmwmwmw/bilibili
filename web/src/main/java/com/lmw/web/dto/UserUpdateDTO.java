package com.lmw.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "用户更新信息")
public class UserUpdateDTO {
    @Schema(description = "用户昵称")
    @Size(min = 2, max = 16, message = "昵称长度应为2-16个字符")
    private String nickname;

    @Schema(description = "性别 0-未知 1-男 2-女")
    private Integer gender;

    @Schema(description = "个性签名")
    @Size(max = 200, message = "个性签名长度应为200个字符")
    private String description;
}
