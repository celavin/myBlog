package com.example.myBlog.entity;



import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Data
public class Article {
    private long id;
    private String title;
    private long authorId;
    private String content;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    private String authorName;

}
