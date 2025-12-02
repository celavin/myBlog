package com.example.myBlog.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myBlog.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
// 只需要继承 BaseMapper，泛型填你的实体类 User
// MP 会自动在这个接口里注入 insert, deleteById, updateById, selectById, selectList 等十几个方法
//哎,又白学了
public interface UserMapper extends BaseMapper<User> {
    
}
