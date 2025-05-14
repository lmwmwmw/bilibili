package com.lmw.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginDto {
    @Schema(description = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String phone;
    
    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;
    
    @Schema(description = "验证码")
    @NotBlank(message = "验证码不能为空")
    private String captcha;

}