package com.elstock.news.repository;

import com.elstock.news.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long>{

}
