package com.example.myBlog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("users") // 1. 绑定数据库表名
public class User {
    // 2. 声明主键，type = AUTO 表示数据库自增 (对应 PG 的 BIGSERIAL)
    @TableId(type = IdType.AUTO)
    private long id;
    
    @NotBlank(message="用户名不能为空")
    @Size(min = 4, max = 20, message = "用户名长度必须在4-20个字符之间")
    private  String userName;

    @Size(min = 8, message = "密码长度不能少于8位")
    private  String password;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
