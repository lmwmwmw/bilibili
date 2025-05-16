package com.lmw.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user_relation")
@Schema(description = "用户关系表")
public class UserRelation extends BaseEntity{

    @Schema(description = "用户关系ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "用户ID")
    @TableField("uid")
    private Integer uid;

    @Schema(description = "被关注用户ID")
    @TableField("follow_uid")
    private Integer followUid;

}
