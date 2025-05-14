package com.lmw.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author lmw
 * @since 2025-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("comment")
@Schema(description = "评论表")
public class Comment extends BaseEntity {
    @Schema(description = "评论id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "评论的视频id")
    private Integer vid;

    @Schema(description = "发送者id")
    private Integer uid;

    @Schema(description = "根节点评论的id,如果为0表示为根节点")
    private Integer rootId;

    @Schema(description = "被回复的评论id，只有root_id为0时才允许为0，表示根评论")
    private Integer parentId;

    @Schema(description = "回复目标用户id")
    private Integer toUserId;

    @Schema(description = "评论内容")
    private String content;

    @Schema(description = "该条评论的点赞数")
    private Integer love;

    @Schema(description = "不喜欢的数量")
    private Integer bad;

    @Schema(description = "是否置顶 0普通 1置顶")
    private Integer isTop;
}
