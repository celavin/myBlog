package com.example.myBlog.mapper;


import com.example.myBlog.entity.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ArticleMapper {

    @Insert("INSERT INTO articles(title, author_id, content, created_time, updated_time) " +
            "VALUES(#{title}, #{authorId}, #{content}, #{createdTime}, #{updatedTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id") // 让 MyBatis 把生成的 ID 带回来
    void insert(Article article);


    @Select("SELECT * FROM articles WHERE id = #{id}")
    Article selectById(Long id);
}
