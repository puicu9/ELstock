package com.elstock.ticker.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TickerSearchDto {
    // searchQuery 검색키워드 (종목명)
    private String searchQuery ;

}
