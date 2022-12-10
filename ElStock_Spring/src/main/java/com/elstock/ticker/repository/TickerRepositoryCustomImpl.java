package com.elstock.ticker.repository;

import com.elstock.ticker.dto.TickerSearchDto;
import com.elstock.ticker.entity.Market;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

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
