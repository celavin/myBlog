package com.example.myBlog.service;

import com.example.myBlog.entity.Article;
import com.example.myBlog.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    public void saveArticle(Article article) {
        // 自动填补时间，不用前端传
        article.setCreatedTime(LocalDateTime.now());
        article.setUpdatedTime(LocalDateTime.now());

        articleMapper.insert(article);
    }

    public Article getArticleById(Long id) {
        return articleMapper.selectById(id);
    }

    public List<Article> getAllArticles() {
        return articleMapper.findAll();
    }
    public void updateArticle(Article article){
        article.setUpdatedTime(LocalDateTime.now());
        articleMapper.update(article);
    }

    public void deleteArticle(long id){
        articleMapper.deleteById(id);
    }
}
