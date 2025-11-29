package com.example.myBlog.mapper;


import com.example.myBlog.entity.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Insert("INSERT INTO articles(title, author_id, content, created_time, updated_time) " +
            "VALUES(#{title}, #{authorId}, #{content}, #{createdTime}, #{updatedTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id") // 让 MyBatis 把生成的 ID 带回来
    void insert(Article article);


    @Select("SELECT * FROM articles WHERE id = #{id}")
    Article selectById(Long id);

    @Select("SELECT a.*, u.username AS authorName " +
            "FROM articles a " +
            "LEFT JOIN users u ON a.author_id = u.id " +
            "ORDER BY a.created_time DESC")
    List<Article> findAll();

    @Update("UPDATE articles SET title=#{title}, content=#{content}, updated_time=#{updatedTime} WHERE id=#{id}")
    void update(Article article);

    @Delete("DELETE FROM articles WHERE id = #{id}")
    void deleteById(long id);
}
