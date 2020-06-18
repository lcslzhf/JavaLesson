package com.lagou.edu.article.service;

import com.lagou.edu.article.model.Article;
import org.springframework.data.domain.Page;

public interface ArticleService {

    Page<Article> getPageList(int pageNum, int pageSize);
}
