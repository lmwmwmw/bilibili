package com.lmw.common.exception;


import com.lmw.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {



    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handl(Exception e) {
        e.printStackTrace();
        return Result.fail();
    }
}
