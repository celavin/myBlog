package com.example.myBlog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myBlog.entity.User;
import com.example.myBlog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public User getUserById(long id){
        return userMapper.selectById(id);
    }

    public User getUserByUsername(String userName){
        // 翻译：SELECT * FROM users WHERE username = ?
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userName); // eq 表示 equal (等于)

        return userMapper.selectOne(wrapper);
    }

    public void insertUser(User user){
        //自动注入创建时间和更新时间
        user.setCreatedTime(LocalDateTime.now());
        user.setUpdatedTime(LocalDateTime.now());

        //密码加密
        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Password);

        userMapper.insert(user);
    }
}
