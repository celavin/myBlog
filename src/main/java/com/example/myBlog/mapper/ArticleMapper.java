package com.example.myBlog.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myBlog.entity.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    @Select("SELECT a.*, u.username AS authorName " +
            "FROM article a " +
            "LEFT JOIN users u ON a.author_id = u.id " + // 注意表名可能是 users
            "ORDER BY a.created_time DESC")
    List<Article> selectAllWithAuthor();
}
