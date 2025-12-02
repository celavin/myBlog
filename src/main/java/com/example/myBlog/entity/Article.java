package com.example.myBlog.entity;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@TableName("articles")
public class Article {
    @TableId(type = IdType.AUTO)
    private long id;
    private String title;
    private long authorId;
    private String content;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    // ⚠️ 重点：authorName 这个字段在数据库里不存在，是关联查询查出来的
    // 我们需要告诉 MP：“插入数据库时，请忽略这个字段，别去表里找它”
    @TableField(exist = false)
    private String authorName;

}
