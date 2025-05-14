package com.lmw.web.service.impl;

import com.lmw.model.entity.UserVideo;
import com.lmw.web.mapper.UserVideoMapper;
import com.lmw.web.service.IUserVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户视频关联表 服务实现类
 * </p>
 *
 * @author lmw
 * @since 2025-05-14
 */
@Service
public class UserVideoServiceImpl extends ServiceImpl<UserVideoMapper, UserVideo> implements IUserVideoService {

}
