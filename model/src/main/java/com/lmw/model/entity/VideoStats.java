package com.lmw.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 视频数据统计表
 * </p>
 *
 * @author lmw
 * @since 2025-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("video_stats")
@Schema(description = "视频数据统计表")
public class VideoStats extends BaseEntity {

    @Schema(description = "视频ID")
    @TableId(value = "vid", type = IdType.AUTO)
    private Integer vid;

    @Schema(description = "播放量")
    private Integer playCount;

    @Schema(description = "弹幕数")
    private Integer danmu;

    @Schema(description = "点赞数")
    private Integer good;

    @Schema(description = "点踩数")
    private Integer bad;

    @Schema(description = "收藏数")
    private Integer collect;

    @Schema(description = "分享数")
    private Integer share;

    @Schema(description = "评论数量统计")
    private Integer comment;
}
