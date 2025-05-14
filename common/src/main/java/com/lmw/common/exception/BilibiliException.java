package com.lmw.common.exception;


import com.lmw.common.result.ResultCodeEnum;
import lombok.Data;

@Data
public class BilibiliException extends RuntimeException {

    private Integer code;

    public BilibiliException(Integer code ,String message) {
        super(message);
        this.code = code;
    }

    public BilibiliException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }
}
