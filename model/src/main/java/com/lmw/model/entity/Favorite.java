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
 * 收藏夹
 * </p>
 *
 * @author lmw
 * @since 2025-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("favorite")
@Schema(description = "收藏夹")
public class Favorite extends BaseEntity {

    @Schema(description = "收藏夹ID")
    @TableId(value = "fid", type = IdType.AUTO)
    private Integer fid;

    @Schema(description = "所属用户ID")
    private Integer uid;

    @Schema(description = "收藏夹类型 1默认收藏夹 2用户创建")
    private Integer type;

    @Schema(description = "对外开放 0隐藏 1公开")
    private Integer visible;

    @Schema(description = "收藏夹封面")
    private String cover;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "简介")
    private String description;

    @Schema(description = "收藏夹中视频数量")
    private Integer count;
}
