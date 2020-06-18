package com.lagou.edu.article.dao;

import com.lagou.edu.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArticleDao extends JpaRepository<Article,Integer>, JpaSpecificationExecutor<Article> {

}
