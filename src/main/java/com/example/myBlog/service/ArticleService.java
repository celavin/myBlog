package com.example.myBlog.service;

import com.example.myBlog.entity.Article;
import com.example.myBlog.mapper.ArticleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    public PageInfo<Article> getAllArticles(Integer pageNum, Integer pageSize) {
        // 1. 【核心咒语】开启分页
        // 这行代码必须紧挨着 mapper 调用，中间不能有其他 SQL 操作
        PageHelper.startPage(pageNum, pageSize);

        // 2. 正常调用 Mapper (SQL 会被自动加上 LIMIT)
        List<Article> list = articleMapper.selectAllWithAuthor();

        // 3. 用 PageInfo 包装查询结果
        // 它会自动计算总页数、总条数等
        return new PageInfo<>(list);
    }
    public void updateArticle(Article article){
        //创建时间不能改变
        article.setCreatedTime(articleMapper.selectById(article.getId()).getCreatedTime());
        //更新时间
        article.setUpdatedTime(LocalDateTime.now());
        articleMapper.updateById(article);
    }

    public void deleteArticle(long id){
        articleMapper.deleteById(id);
    }
}
