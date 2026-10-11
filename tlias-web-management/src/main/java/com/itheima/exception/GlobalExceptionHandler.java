package com.itheima.exception;


import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
/**
 * 全局异常处理器
 */
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e){
        log.error("程序出错了~",e);
        return Result.error("出错了~，请联系管理员~");
    }

    /**
     * 处理重复数据异常
     */
    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error("程序出错了~",e);
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry"); // 找到"Duplicate entry"的索引
        String errMsg=message.substring(i); // 截取"Duplicate entry"开始的字符串
        String []arr=errMsg.split(" "); // 使用空格分割字符串
        return Result.error(arr[2]+"已存在");
    }

}
