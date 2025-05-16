package com.lmw.web.controller.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/statistics")
@Tag(name = "用户统计接口")
public class UserStatisticsController {
}
