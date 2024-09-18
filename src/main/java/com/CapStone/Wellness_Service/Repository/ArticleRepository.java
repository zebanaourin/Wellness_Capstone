package com.CapStone.Wellness_Service.Repository;

import com.CapStone.Wellness_Service.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{

    List<Article> findByCategory(String category);
}
