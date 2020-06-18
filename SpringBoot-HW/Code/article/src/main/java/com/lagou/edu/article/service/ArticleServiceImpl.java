package com.lagou.edu.article.service;

import com.lagou.edu.article.dao.ArticleDao;
import com.lagou.edu.article.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Override
    public Page<Article> getPageList(int pageNum, int pageSize) {

        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Article> articles = articleDao.findAll(pageable);

        return articles;
    }
}
