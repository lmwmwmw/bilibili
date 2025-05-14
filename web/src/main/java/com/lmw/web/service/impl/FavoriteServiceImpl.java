package com.lmw.web.service.impl;

import com.lmw.model.entity.Favorite;
import com.lmw.web.mapper.FavoriteMapper;
import com.lmw.web.service.IFavoriteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收藏夹 服务实现类
 * </p>
 *
 * @author lmw
 * @since 2025-05-14
 */
@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite> implements IFavoriteService {

}
