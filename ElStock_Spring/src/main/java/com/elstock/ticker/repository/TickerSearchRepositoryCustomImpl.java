package com.elstock.ticker.repository;

import com.elstock.ticker.dto.TickerSearchDto;
import com.elstock.ticker.entity.Market;
import com.elstock.ticker.entity.QMarket;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class TickerSearchRepositoryCustomImpl implements TickerSearchRepositoryCustom {
    private JPAQueryFactory queryFactory ;

    public TickerSearchRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    // 검색 조건
    private BooleanExpression searchQueryCondition(String searchQuery){
        // searchQuery : 검색 키워드
        if(StringUtils.equals("ticker_name", searchQuery)){
            // 티커 이름이 검색되면
            return QMarket.market.ticker_name.like("%" + searchQuery + "%");
        } else if (StringUtils.equals("ticker_code", searchQuery)){
            // 티커 코드가 검색되면
            return QMarket.market.ticker_code.like("%" + searchQuery + "%");
        } else {
            return null ;
        }

    }

    @Override
    public Page<Market> getTickerPage(TickerSearchDto dto, Pageable pageable) {
        QueryResults<Market> result = this.queryFactory
                .selectFrom(QMarket.market)
                .where(searchQueryCondition(dto.getSearchQuery()))
                .orderBy(QMarket.market.ticker_name.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Market> content = result.getResults();

        long total = result.getTotal();


        return new PageImpl<>(content, pageable, total);
    }
}
