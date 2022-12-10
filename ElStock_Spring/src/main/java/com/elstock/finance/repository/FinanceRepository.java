package com.elstock.finance.repository;

import com.elstock.finance.Entity.Finance;
import com.elstock.finance.dto.FinanceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FinanceRepository extends JpaRepository<Finance, String> {

    @Query(" select new com.elstock.finance.dto.FinanceDto( f.dates, f.revenue, f.operating_profit, f.earnings, f.operation_income, f.net_profit_rate, f.roe, f.debt_ratio, f.quick_ratio, f.reserve_ratio, f.eps, f.per, f.bps, f.pbr, f.ticker_code) " +
            " from Finance f " +
            " where f.ticker_code = :ticker_code" +
            " order by f.dates asc")
    List<FinanceDto> findByFinanceDetail(@Param("ticker_code") String ticker_code);



//    @Query(value=" select * from fss f " +
//            " where f.ticker_code = :ticker_code" +
//            " order by f.dates asc", nativeQuery = true)
//    List<FinanceDto> findByFinanceDetail(@Param("ticker_code") String ticker_code);

//    @Query(value=" select f from Finance f " +
//            " where f.ticker_code = :ticker_code" +
//            " order by f.date asc")
//    List<FinanceDto> findByFinanceDetail(@Param("ticker_code") String ticker_code);




}
