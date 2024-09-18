package com.CapStone.Wellness_Service.Service;

import com.CapStone.Wellness_Service.Entity.Article;
import com.CapStone.Wellness_Service.Repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;


    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getArticles(String category) {
        return articleRepository.findByCategory(category);
    }

    public List<Article> getAllArticles(){
        return articleRepository.findAll();
    }


}
