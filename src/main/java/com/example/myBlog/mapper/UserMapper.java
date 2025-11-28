package com.example.myBlog.mapper;


import com.example.myBlog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE id = #{id}")
    User selectById(long id);
    @Select("SELECT * FROM users WHERE username = #{userName}")
    User selectByUserName(String userName);
}
