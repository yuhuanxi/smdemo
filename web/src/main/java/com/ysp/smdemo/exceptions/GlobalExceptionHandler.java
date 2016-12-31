package com.ysp.smdemo.exceptions;

import com.ysp.smdemo.common.exceptions.DataNotFoundException;
import com.ysp.smdemo.common.utils.ReturnCode;
import com.ysp.smdemo.controllers.BaseController;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: shipeng.yu
 * @time: 2016年10月06日 下午3:16
 * @version: 1.0
 * @since: 1.0
 * @description: 全局异常捕获
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends BaseController {

    //添加全局异常处理流程，根据需要设置需要处理的异常，本文以MethodArgumentNotValidException为例
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Object methodArgumentNotValidHandler(HttpServletRequest request,
                                                BindException exception) {
        //按需重新封装需要返回的错误信息
        List<ArgumentInvalidResult> invalidArguments = new ArrayList<>();
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            ArgumentInvalidResult invalidArgument = new ArgumentInvalidResult(error.getField(), error.getRejectedValue(), error.getDefaultMessage());
            invalidArguments.add(invalidArgument);
        }

        return renderJsonAjaxResult(true, ReturnCode.PARAMER_NOT_INVALID.getCode(), ReturnCode.PARAMER_NOT_INVALID.getMsg(), invalidArguments);
    }

    @ExceptionHandler(value = DataNotFoundException.class)
    @ResponseBody
    public Object dataNotFoundExceptionHandler(HttpServletRequest request, DataNotFoundException exception) {

        // 用户不存在
        if (ReturnCode.USER_NOT_FOUND.name().equals(exception.getMessage())) {
            return renderJsonFail(ReturnCode.USER_NOT_FOUND.getCode(), ReturnCode.USER_NOT_FOUND.getMsg());
        }

        // 通用数据不存在返回格式
        return renderJsonFail(ReturnCode.DATA_NOT_FOUND.getCode(), ReturnCode.DATA_NOT_FOUND.getMsg());
    }
}
