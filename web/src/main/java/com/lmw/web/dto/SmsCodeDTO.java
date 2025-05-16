package com.lmw.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "短信验证码请求")
public class SmsCodeDTO {
    
    @Schema(description = "手机号")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;
    
    @Schema(description = "业务类型：login-登录, register-注册")
    @NotBlank(message = "业务类型不能为空")
    private String type;
}