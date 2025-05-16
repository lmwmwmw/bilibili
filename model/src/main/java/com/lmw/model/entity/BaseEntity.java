package com.lmw.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {


    @Schema(description = "创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Schema(description = "更新时间")
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @Schema(description = "逻辑删除 0-未删除 1-已删除")
    @JsonIgnore
    @TableField("is_deleted")
    @TableLogic
    private Byte isDeleted;

}