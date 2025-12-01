package com.example.myBlog.mapper;


import com.example.myBlog.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE id = #{id}")
    User selectById(long id);
    @Select("SELECT * FROM users WHERE username = #{userName}")
    User selectByUserName(String userName);

    @Insert("INSERT INTO users(username,password) VALUES(#{userName},#{password})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insertUser(User user);
}
