package com.elstock.ticker.repository;

import com.elstock.ticker.dto.TickerSearchDto;
import com.elstock.ticker.entity.Market;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TickerSearchRepositoryCustom {

    // 메인페이지에서 티커 검색 목록을 보여주고자 할 때 사용
    Page<Market> getTickerPage(TickerSearchDto dto, Pageable pageable) ;

}
