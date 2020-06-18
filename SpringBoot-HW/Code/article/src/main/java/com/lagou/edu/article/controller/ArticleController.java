package com.lagou.edu.article.controller;

import com.lagou.edu.article.model.Article;
import com.lagou.edu.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/index")
    public String findByPage(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "3") int pageSize, ModelMap map){
        Page<Article> allByPage = articleService.getPageList(pageNum,pageSize);
        map.put("page",allByPage);
        int totalPages = allByPage.getTotalPages();
        List<Integer> pages = new ArrayList<Integer>();
        for (int i = 0; i <= totalPages-1; i++) {
            pages.add(i);
        }
        map.put("pageNum",pages);
        return "client/index";
    }

}
