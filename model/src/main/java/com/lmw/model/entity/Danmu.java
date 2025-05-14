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
 * 弹幕表
 * </p>
 *
 * @author lmw
 * @since 2025-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("danmu")
@Schema(description = "弹幕表")
public class Danmu extends BaseEntity {

    @Schema(description = "弹幕ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "视频ID")
    private Integer vid;

    @Schema(description = "用户ID")
    private Integer uid;

    @Schema(description = "弹幕内容")
    private String content;

    @Schema(description = "字体大小")
    private Integer fontsize;

    @Schema(description = "弹幕模式 1滚动 2顶部 3底部")
    private Integer mode;

    @Schema(description = "弹幕颜色 6位十六进制标准格式")
    private String color;

    @Schema(description = "弹幕所在视频的时间点")
    private Double timePoint;

    @Schema(description = "弹幕状态 1默认过审 2被举报审核中 3删除")
    private Integer state;

    @Schema(description = "发送弹幕的日期时间")
    private LocalDateTime createDate;
}
