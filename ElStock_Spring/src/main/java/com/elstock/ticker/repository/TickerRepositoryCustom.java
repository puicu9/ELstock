package com.elstock.ticker.repository;

import com.elstock.market.entity.Market;
import com.elstock.ticker.dto.TickerSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TickerRepositoryCustom {

    // 메인페이지에서 검색하고자 할 때 사용
    Page<Market> getMainTickerPage(TickerSearchDto dto, Pageable pageable) ;
}
