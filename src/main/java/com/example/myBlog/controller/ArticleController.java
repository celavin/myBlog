package com.example.myBlog.controller;


import com.example.myBlog.entity.Article;
import com.example.myBlog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping
    public String publish(@RequestBody Article article) {
        articleService.saveArticle(article);
        return "发布成功！文章ID是：" + article.getId();
    }

    @GetMapping("/{id}")
    public Article getArticle(@PathVariable long id){
        return articleService.getArticleById(id);
    }
}
