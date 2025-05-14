package com.lmw.web.service.impl;

import com.lmw.model.entity.FavoriteVideo;
import com.lmw.web.mapper.FavoriteVideoMapper;
import com.lmw.web.service.IFavoriteVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 视频收藏夹关系表 服务实现类
 * </p>
 *
 * @author lmw
 * @since 2025-05-14
 */
@Service
public class FavoriteVideoServiceImpl extends ServiceImpl<FavoriteVideoMapper, FavoriteVideo> implements IFavoriteVideoService {

}
