package com.elstock.ticker.repository;

import com.elstock.ticker.dto.TickerSearchDto;
import com.elstock.ticker.entity.Market;
import com.elstock.ticker.entity.QMarket;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
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

    // 날짜 필터
//    private BooleanExpression searchDateFilter(LocalDate searchStartDate, LocalDate searchEndDate) {
//        String pattern = "yyyy-MM-dd";
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//        String now = sdf.format(date);
//
//        //goe, loe 사용
//        BooleanExpression isGoeStartDate = now.goe(LocalDateTime.of(searchStartDate, LocalTime.MIN));
//        BooleanExpression isLoeEndDate = post.createdAt.loe(LocalDateTime.of(searchEndDate, LocalTime.MAX).withNano(0));
//
//        return Expressions.allOf(isGoeStartDate, isLoeEndDate);
//    }

//    private BooleanExpression searchdateQuery(LocalDateTime startDate, LocalDateTime endDate) {
//
//        BooleanExpression searchDate = null;
//        if (startDate == null && endDate == null){
//            LocalDateTime currentStartDate = LocalDateTime.of(LocalDate.now(),LocalTime.of(0,0,0));
//            LocalDateTime currentEndDate = LocalDateTime.of(LocalDate.now(),LocalTime.of(23,59,59));
//            //날짜데이터 없으면 하루 시작과 끝
//            searchDate = QMarket.market.date.between(currentStartDate.toString(), currentEndDate.toString());
//        } else if (startDate != null && endDate == null) {
//            LocalDateTime startDateTime = LocalDateTime.of(LocalDate.from(startDate),LocalTime.of(0,0,0));
//            //시작 날짜만 있으면 있으면 시작날부터 쭉 뒤로
//            searchDate = QMarket.market.date.goe(startDateTime.toString());
//        } else if (startDate == null) {
//            //시작날짜 없으면 해당하는 달 1일 부터 조회일 까지
//            LocalDate firstMonthDate = LocalDate.from(endDate.with(TemporalAdjusters.firstDayOfMonth()));
//            LocalDateTime startDateTime = LocalDateTime.of(firstMonthDate,LocalTime.of(0,0,0));
//            searchDate = QMarket.market.date.between(startDateTime.toString(),endDate.toString());
//        } else {
//            searchDate = QMarket.market.date.between(startDate.toString(), endDate.toString());
//        }
//        System.out.println("searchDate : " + searchDate);
//        return searchDate;
//    }

//    private BooleanExpression dateCondition(){
//        BooleanExpression dateQuery = null ;
//        LocalDateTime currentDate = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0)) ;
//
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date now = new Date();
////        String today = format(now);
//
//
//        return dateQuery;
//    }


    @Override
    public Page<Market> getTickerPage(TickerSearchDto dto, Pageable pageable) {
        LocalDateTime dateTime = LocalDateTime.now();

        String dateTimeString = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00"));
//        LocalDateTime today = LocalDateTime.parse(dateTime, dateTimeString);


        List<Market> content = this.queryFactory
                .selectFrom(QMarket.market)
                .where(searchQueryCondition(
                        dto.getSearchQuery())

                )
                .orderBy(QMarket.market.ticker_name.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

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

//    private BooleanExpression searchDateQuery(StringPath date) {
//        BooleanExpression searchDate = null;
//
////        Date now = new Date();
////        String today = now.toString();
//        return QMarket.market.eq();
//
//
//
//
//        if (startDate == null && endDate == null){
//            LocalDateTime currentStartDate = LocalDateTime.of(LocalDate.now(),LocalTime.of(0,0,0));
//            LocalDateTime currentEndDate = LocalDateTime.of(LocalDate.now(),LocalTime.of(23,59,59));
//            //날짜데이터 없으면 하루 시작과 끝
//            searchDate = QMarket.market.date.between(currentStartDate.toString(), currentEndDate.toString());
//
//        } else if (startDate != null && endDate == null) {
//            LocalDateTime startDateTime = LocalDateTime.of(LocalDate.from(startDate),LocalTime.of(0,0,0));
//            //시작 날짜만 있으면 있으면 시작날부터 쭉 뒤로
//            searchDate = QMarket.market.date.goe(startDateTime.toString());
//        } else if (startDate == null) {
//            //시작날짜 없으면 해당하는 달 1일 부터 조회일 까지
//            LocalDate firstMonthDate = LocalDate.from(endDate.with(TemporalAdjusters.firstDayOfMonth()));
//            LocalDateTime startDateTime = LocalDateTime.of(firstMonthDate,LocalTime.of(0,0,0));
//            searchDate = QMarket.market.date.between(startDateTime.toString(),endDate.toString());
//        } else {
//            searchDate = QMarket.market.date.between(startDate.toString(), endDate.toString());
//        }
//
//        System.out.println("searchDate : " + searchDate);
//        return searchDate;
//    }

//    @Override
//    public Page<Tuple> getTickerPage(TickerSearchDto dto, Pageable pageable) {
//        QMarket market = QMarket.market;
//
//        QueryResults<Tuple> result = this.queryFactory
//                .select(market.ticker_code, market.ticker_name)
//                .from(market)
//                .where(searchQueryCondition(dto.getSearchQuery()))
//                .orderBy(market.ticker_name.asc())
//                .groupBy(market.ticker_name, market.ticker_code)
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetchResults();
//        List<Tuple> content = result.getResults();
//
//        // 결과 집합의 총 개수 반환
//        long total = result.getTotal();
//
//        return new PageImpl<>(content, pageable, total);
//    }
}
