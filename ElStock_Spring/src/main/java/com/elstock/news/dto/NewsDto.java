package com.elstock.news.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewsDto {
    private Long no;

    private String news_url;
    private String news_header;
    private String news_body;
    private String news_img;
}
