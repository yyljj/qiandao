package com.jaxlying.www.controller.admin;

import com.jaxlying.www.model.Article;
import com.jaxlying.www.repository.ArticleRepository;
import com.jaxlying.www.util.LinkPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by jaxlying on 2016/9/24.
 */
@Controller
@RequestMapping("/admin")
public class AdminArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @RequestMapping(value = "/articles",method = RequestMethod.GET)
    public String getArticles(Model model,
                              @RequestParam(value = "page", defaultValue = "0") Integer page,
                              @RequestParam(value = "size", defaultValue = "8") Integer size){
        Pageable pageable = new PageRequest(page,size);
        Page<Article> pages = articleRepository.findByDeleteAtOrderByIdDesc(0,pageable);
        model.addAttribute("page", new LinkPage<>(pages,"/admin/articles"));
        return "admin/home";
    }
}