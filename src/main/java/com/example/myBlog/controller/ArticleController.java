package com.example.myBlog.controller;


import com.example.myBlog.common.Result;
import com.example.myBlog.entity.Article;
import com.example.myBlog.entity.User;
import com.example.myBlog.service.ArticleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result<String> publish(@RequestBody Article article, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return Result.error(401, "未登录");
        }

        article.setAuthorId(currentUser.getId());

        articleService.saveArticle(article);

        return Result.success("发布成功！文章ID是：" + article.getId());
    }

    @GetMapping("/{id}")
    public Article getArticle(@PathVariable long id){
        //待完善,需检验是否存在,否则报错
        return articleService.getArticleById(id);
    }

    @GetMapping
    public List<Article> list() {
        return articleService.getAllArticles();
    }

    @PutMapping
    public Result<String> update(@RequestBody Article article){
        articleService.updateArticle(article);
        return Result.success("修改成功！");
    }
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable long id){
        articleService.deleteArticle(id);
        return Result.success("已删除id为"+id+"的文章!");
    }
}
