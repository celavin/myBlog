package com.example.myBlog.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class User {
    private long id;
    @NotBlank(message="用户名不能为空")
    @Size(min = 4, max = 20, message = "用户名长度必须在4-20个字符之间")
    private  String userName;

    @Size(min = 8, message = "密码长度不能少于8位")
    private  String password;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
