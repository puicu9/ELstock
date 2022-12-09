package com.elstock.news.service;

import com.elstock.news.dto.NewsDto;
import com.elstock.news.entity.News;
import com.elstock.news.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NewsService {
    private final NewsRepository newsRepository;

    public List<NewsDto> getNews(){
        List<News> news = newsRepository.findAll();

        List<NewsDto> newsList = new ArrayList<>();

        for(News bean : news){
            NewsDto list = NewsDto.builder()
                    .no(bean.getNo())
                    .news_body(bean.getNews_body())
                    .news_header(bean.getNews_header())
                    .news_img(bean.getNews_img())
                    .news_url(bean.getNews_url())
                    .build();
            newsList.add(list);
        }
        return newsList;
    }
}
