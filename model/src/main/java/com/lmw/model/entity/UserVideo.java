package com.lmw.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户视频关联表
 * </p>
 *
 * @author lmw
 * @since 2025-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user_video")
@Schema(description = "用户视频关联表")
public class UserVideo extends BaseEntity {

    @Schema(description = "用户视频关联id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "观看视频的用户id")
    private Integer uid;

    @Schema(description = "观看的视频id")
    private Integer vid;

    @Schema(description = "观看次数")
    private Integer playCount;

    @Schema(description = "点赞 0没赞 1已点赞")
    private Integer love;

    @Schema(description = "不喜欢 0没点 1已不喜欢")
    private Integer unlove;

    @Schema(description = "收藏 0没收藏 1已收藏")
    private Integer collect;

    @Schema(description = "最近播放时间")
    private LocalDateTime playTime;

    @Schema(description = "点赞时间")
    private LocalDateTime loveTime;
}
