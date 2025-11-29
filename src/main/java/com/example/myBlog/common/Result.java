package com.example.myBlog.common;
import lombok.Data;

@Data // 自动生成 get/set 方法
public class Result<T> {
    private int code;      // 状态码：200成功，500失败
    private String msg;    // 提示信息
    private T data;        // 返回的数据（文章对象、列表等）

    // 1. 成功时候用的方法（带数据）
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.code = 200;
        result.msg = "success";
        result.data = data;
        return result;
    }

    // 2. 成功时候用的方法（只带消息，不带数据，比如修改成功）
    public static <T> Result<T> success(String msg) {
        Result<T> result = new Result<>();
        result.code = 200;
        result.msg = msg;
        result.data = null;
        return result;
    }

    // 3. 失败时候用的方法（这是刚才新加的）
    public static <T> Result<T> error(int code, String msg) {
        Result<T> result = new Result<>();
        result.code = code;
        result.msg = msg;
        result.data = null;
        return result;
    }
}