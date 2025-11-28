package com.example.myBlog.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private long id;
    private  String userName;
    private  String password;
    private Date createdTime;
    private Date updatedTime;
}
