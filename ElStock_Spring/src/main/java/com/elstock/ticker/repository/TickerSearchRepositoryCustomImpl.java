package com.elstock.ticker.repository;

import com.elstock.market.entity.Market;
import com.elstock.market.entity.QMarket;
import com.elstock.ticker.dto.TickerSearchDto;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TickerSearchRepositoryCustomImpl implements TickerSearchRepositoryCustom {
    private JPAQueryFactory queryFactory ;

    public TickerSearchRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    // 검색 조건
    private BooleanExpression searchQueryCondition(String searchQuery){
        // searchQuery : 검색 키워드
        if(searchQuery != null || !searchQuery.equals("") || searchQuery != "" ) {
            // 티커 이름이 검색되면
            return QMarket.market.ticker_name.like("%" + searchQuery + "%");
        }
        return null ;
    }




//        if(StringUtils.equals("ticker_name", searchQuery)){
//            System.out.println("aaaaa");
//            // 티커 이름이 검색되면
//            return QMarket.market.ticker_name.like("%" + searchQuery + "%");
//        } else if (StringUtils.equals("ticker_code", searchQuery)){
//            System.out.println("bbbbb");
//            // 티커 코드가 검색되면
//            return QMarket.market.ticker_code.like("%" + searchQuery + "%");
//        } else {
//            System.out.println("ccccc");
//            return null ;
//        }

    BooleanExpression dateRange(){
        LocalDateTime dateTime = LocalDateTime.now();
        dateTime = dateTime.minusDays(2) ;

//        BooleanExpression dateAfter = QMarket.market.date.after(dateTime);
        BooleanExpression dateAfter = QMarket.market.date.after(dateTime);
        return dateAfter ;
    }


    @Override
    public Page<Market> getTickerPage(TickerSearchDto dto, Pageable pageable) {
        LocalDateTime dateTime = LocalDateTime.now();

        QueryResults<Market> result = this.queryFactory
                .selectFrom(QMarket.market)
                .where(searchQueryCondition(
                                dto.getSearchQuery())
                        , dateRange()
                )
                .orderBy(QMarket.market.ticker_name.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Market> content = result.getResults() ;
        long total = result.getTotal() ; // 결과 집합의 총 개수를 반환해줍니다.

        return new PageImpl<>(content, pageable, total);
    }



}