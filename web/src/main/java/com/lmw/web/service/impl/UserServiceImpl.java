package com.lmw.web.service.impl;

import com.lmw.model.entity.User;
import com.lmw.web.mapper.UserMapper;
import com.lmw.web.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lmw
 * @since 2025-05-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
