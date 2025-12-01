package com.example.myBlog.service;

import com.example.myBlog.entity.User;
import com.example.myBlog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public User getUserById(long id){
        return userMapper.selectById(id);
    }

    public User getUserByUsername(String userName){
        return userMapper.selectByUserName(userName);
    }

    public void insertUser(User user){
        //自动注入创建时间和更新时间
        user.setCreatedTime(LocalDateTime.now());
        user.setUpdatedTime(LocalDateTime.now());
        userMapper.insertUser(user);
    }
}
