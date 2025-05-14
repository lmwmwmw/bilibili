package com.lmw.web.dto;

import com.lmw.model.enums.GraphItemType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "更新头像或背景图片")
public class GraphUpdateDto {

    @Schema(description = "图片类型")
    private GraphItemType graphItemType;


    @Schema(description = "图片url")
    private String url;


}
