package com.elstock.ticker.repository;

import com.elstock.ticker.dto.TickerSearchDto;
import com.elstock.ticker.entity.Market;
import com.elstock.ticker.entity.QMarket;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.tomcat.jni.Local;
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

        BooleanExpression dateEq = QMarket.market.date.after(dateTime);
        return dateEq ;
    }


    @Override
    public Page<Market> getTickerPage(TickerSearchDto dto, Pageable pageable) {
        LocalDateTime dateTime = LocalDateTime.now();

        String dateTimeString = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00"));
//        LocalDateTime today = LocalDateTime.parse(dateTime, dateTimeString);

        List<Market> content = this.queryFactory
                .selectFrom(QMarket.market)
                .where(searchQueryCondition(
                        dto.getSearchQuery())
                        , dateRange()
//                        ,QMarket.market.date.eq(LocalDateTime.parse(dateTimeString))
                )
                .orderBy(QMarket.market.ticker_name.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
//
//                .select(QMarket.market.ticker_code,
//                        QMarket.market.ticker_name
//                )
//                .from(QMarket.market)
//                .where(searchQueryCondition(dto.getSearchQuery()))
//                .orderBy(QMarket.market.ticker_name.asc())
//                .groupBy(QMarket.market.ticker_name, QMarket.market.ticker_code)
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();

        return new PageImpl<>(content, pageable, content.size());
    }

//    @Override
//    public Page<Market> getTickerPage(TickerSearchDto dto, Pageable pageable) {
//        QMarket market = QMarket.market;
//
//        QueryResults<Market> result = this.queryFactory
//                .select(market.ticker_code, market.ticker_name)
//                .from(market)
//                .where(searchQueryCondition(dto.getSearchQuery())
//                )
//                .orderBy(market.ticker_name.asc())
//                .groupBy(market.ticker_name, market.ticker_code)
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//        List<Market> content = result.getResults();
//
//        // 결과 집합의 총 개수 반환
//        long total = result.getTotal();
//
//        return new PageImpl<>(content, pageable, total);
//    }

//    BooleanExpression dateEq(TickerSearchDto dto){
//        LocalDateTime dateTime = LocalDateTime.now();
//        String dateTimeString = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00"));
//
//
//
//
//        return dateTimeString != null ? QMarket.market.date.eq(dateTimeString == ) : null;
//    }


}
