package com.lmw.web.service.impl;

import com.lmw.model.entity.VideoStats;
import com.lmw.web.mapper.VideoStatsMapper;
import com.lmw.web.service.IVideoStatsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 视频数据统计表 服务实现类
 * </p>
 *
 * @author lmw
 * @since 2025-05-14
 */
@Service
public class VideoStatsServiceImpl extends ServiceImpl<VideoStatsMapper, VideoStats> implements IVideoStatsService {

}
