//package com.elstock.market.repository;
//
//import com.elstock.company.entity.QCompany;
//import com.elstock.market.entity.QMarket;
//import com.querydsl.core.Tuple;
//import com.querydsl.core.types.dsl.BooleanExpression;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//
//import javax.persistence.EntityManager;
//import java.time.LocalDateTime;
//import java.util.List;
//
//public abstract class MarketListRepositoryImpl implements MarketListRepository {
//
//    private JPAQueryFactory queryFactory ;
//
//    public MarketListRepositoryImpl(EntityManager em){
//        this.queryFactory = new JPAQueryFactory(em);
//    }
//
//    BooleanExpression dateRange(){
//        LocalDateTime dateTime = LocalDateTime.now();
//        dateTime = dateTime.minusDays(1) ;
//
//        BooleanExpression dateAfter = QMarket.market.date.after(dateTime);
//        return dateAfter ;
//    }
//
//
//    @Override
//    public List<Tuple> getMarketLists() {
//        QMarket market = QMarket.market;
//        QCompany company = QCompany.company;
//
//        List<Tuple> result = this.queryFactory
//                .select(market.open,
//                        market.high,
//                        market.low,
//                        market.close,
//                        market.volume,
//                        market.total,
//                        market.rate,
//                        market.ticker_code,
//                        market.ticker_name,
//                        company.company_rank
//                )
//                .from(market)
//                .join(company)
//                .on(market.ticker_code.eq(company.ticker_code))
//                .where(dateRange())
//                .groupBy(market.open,
//                        market.high,
//                        market.low,
//                        market.close,
//                        market.volume,
//                        market.total,
//                        market.rate,
//                        market.ticker_code,
//                        market.ticker_name,
//                        company.company_rank)
//                .orderBy(company.company_rank.asc())
//                .fetch();
//        return result;
//    }
//}
