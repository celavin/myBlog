package com.example.myBlog.handler;

import com.example.myBlog.common.Result;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdvice = @ControllerAdvice + @ResponseBody
// 意思是：这个类是所有 Controller 的“智囊团”，而且返回的数据自动转 JSON
@RestControllerAdvice
public class GlobalExceptionHandler {

    // @ExceptionHandler 告诉 Spring：
    // 如果系统里抛出了 Exception (包括所有的子类错误)，都交给我来处理！

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleValidationException(MethodArgumentNotValidException e) {
        // 1. 获取所有的错误信息
        BindingResult bindingResult = e.getBindingResult();

        // 2. 提取第一条错误提示（比如 "用户名长度必须在4-20字符之间"）
        // 我们只返回第一条给用户看就行了，不用把所有错误都列出来
        String msg = bindingResult.getFieldError().getDefaultMessage();

        // 3. 返回 400 状态码
        return Result.error(400, msg);
    }
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        // 1. 在后台打印堆栈信息（方便咱们程序员排错）
        e.printStackTrace();

        // 2. 给前端返回一个漂亮的 JSON
        // 500 通常代表服务器内部错误
        return Result.error(500, "系统开小差了：" + e.getMessage());
    }
}