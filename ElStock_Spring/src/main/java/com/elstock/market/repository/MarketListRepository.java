package com.elstock.market.repository;

import com.elstock.market.entity.Market;
import com.querydsl.core.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarketListRepository extends JpaRepository<Market, String> {

//    List<Tuple> getMarketLists() ;

//    @Query(" select new com.elstock.market.dto.MarketDto(m.date, m.open, m.high, m.low, m.close, m.volume, m.total, m.rate, m.ticker_code, m.ticker_name, c.company_rank)" +
//            " from Market m " +
//            " join Company c " +
//            " on (m.ticker_code=c.ticker_code)" +
//            " where m.date=:today " +
//            " group by (m.open, m.high, m.low, m.close, m.volume, m.total, m.rate, m.ticker_code, m.ticker_name, c.company_rank) " +
//            " order by c.company_rank asc ")
//    List<MarketDto> findTickerListByDate(@Param("today") LocalDateTime today);

//    List<MarketDto> getMarketList(MarketDto marketDto);


}
