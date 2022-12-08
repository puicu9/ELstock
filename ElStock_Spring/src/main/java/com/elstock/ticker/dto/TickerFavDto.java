package com.elstock.ticker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TickerFavDto {

    private String fav_code; // 사이드바에서 넘겨줄 회사코드
    private String fav_name; // 보여줄 회사명
}
