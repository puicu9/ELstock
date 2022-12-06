package com.elstock.ticker.repository;

import com.elstock.ticker.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface TickerSearchRepository extends JpaRepository<Market, String>, QuerydslPredicateExecutor<Market>, TickerSearchRepositoryCustom {

    // 티커 이름으로 데이터를 조회

}
