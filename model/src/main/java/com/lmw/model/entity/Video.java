package com.lmw.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 视频表
 * </p>
 *
 * @author lmw
 * @since 2025-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("video")
@Schema(description = "视频表")
public class Video extends BaseEntity {

    @Schema(description = "视频id")
    @TableId(value = "vid", type = IdType.AUTO)
    private Integer vid;

    @Schema(description = "上传视频的用户id")
    private Integer uid;

    @Schema(description = "视频标题")
    private String title;

    @Schema(description = "类型 1自制 2转载")
    private Integer type;

    @Schema(description = "作者声明 0不声明 1未经允许禁止转载")
    private Integer auth;

    @Schema(description = "播放总时长 单位秒")
    private Double duration;

    @Schema(description = "标签 回车分隔")
    private String tags;

    @Schema(description = "简介")
    private String descr;

    @Schema(description = "封面url")
    private String coverUrl;

    @Schema(description = "视频url")
    private String videoUrl;

    @Schema(description = "状态 0审核中 1已过审 2未通过 3已删除")
    private Integer status;

    @Schema(description = "上传时间")
    private LocalDateTime uploadDate;

    @Schema(description = "删除时间")
    private LocalDateTime deleteDate;
}
