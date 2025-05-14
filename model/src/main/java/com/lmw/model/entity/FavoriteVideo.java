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
 * 视频收藏夹关系表
 * </p>
 *
 * @author lmw
 * @since 2025-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("favorite_video")
@Schema(description = "视频收藏夹关系表")
public class FavoriteVideo extends BaseEntity {

    @Schema(description = "收藏夹视频关系ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "视频ID")
    private Integer vid;

    @Schema(description = "收藏夹ID")
    private Integer fid;

    @Schema(description = "收藏时间")
    private LocalDateTime time;
}
