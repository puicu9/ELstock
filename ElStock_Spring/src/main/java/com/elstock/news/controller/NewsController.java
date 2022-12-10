package com.elstock.news.controller;

import com.elstock.chart.service.TeslaService;
import com.elstock.common.controller.CommonController;
import com.elstock.news.dto.NewsDto;
import com.elstock.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;
    private final CommonController commonController;

    @GetMapping(value = {"/news", "/"})
    public String test(Model model){
        List<NewsDto> list =this.newsService.getNews();
        model.addAttribute("list", list);
        return "/news/_news";
    }

//    @GetMapping(value = "/news")
//    public String test2(){
//        return "/news/_news";
//    }

    @GetMapping(value = "/news/load")
    public ResponseEntity<List<NewsDto>> loadNews(Principal principal, Model model){

        commonController.commonData(principal, model);
        return new ResponseEntity<List<NewsDto>>(this.newsService.getNews(), HttpStatus.OK);
    }
}
