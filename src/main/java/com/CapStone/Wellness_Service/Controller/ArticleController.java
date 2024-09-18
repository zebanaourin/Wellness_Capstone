package com.CapStone.Wellness_Service.Controller;

import com.CapStone.Wellness_Service.Entity.Article;
import com.CapStone.Wellness_Service.Service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<Article> getAllArticles(){
        return articleService.getAllArticles();
    }

    @GetMapping("/category/{category}")
    public List<Article> getArticlesByCategory(@PathVariable String category){
        return articleService.getArticles(category);
    }
}
