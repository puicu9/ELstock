package com.elstock.ticker.repository;

import com.elstock.market.entity.Market;
import com.elstock.ticker.dto.TickerSearchDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

public class TickerRepositoryCustomImpl implements TickerRepositoryCustom{
    private JPAQueryFactory queryFactory ;

    public TickerRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

//    private BooleanExpression searchByCondition(String searchQuery){
//        // searchQuery : 검색 키워드
//
//        if(StringUtils.equals(searchQuery)){
//            return Q
//        }
//
//    }

    @Override
    public Page<Market> getMainTickerPage(TickerSearchDto dto, Pageable pageable) {
        return null;
    }
}
