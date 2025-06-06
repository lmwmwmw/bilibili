package com.lmw.web.mapper;

import com.lmw.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author lmw
 * @since 2025-05-14
 */
public interface UserMapper extends BaseMapper<User> {

    void incrementFollowCount(Long userId);

    void incrementFollowerCount(Integer userId);
}
