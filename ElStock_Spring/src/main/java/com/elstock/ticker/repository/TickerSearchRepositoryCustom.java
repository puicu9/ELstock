package com.elstock.ticker.repository;

import com.elstock.ticker.dto.TickerSearchDto;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TickerSearchRepositoryCustom {

    // 메인페이지에서 티커 검색 목록을 보여주고자 할 때 사용
    Page<Tuple> getTickerPage(TickerSearchDto dto, Pageable pageable) ;

}
