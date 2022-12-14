package com.elstock.theme.repository;


import com.elstock.theme.entity.Ticker_by_theme;
import com.elstock.theme.dto.Ticker_by_themeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ticker_by_themeRepository extends JpaRepository<Ticker_by_theme,Long> {

    @Query(" select new com.elstock.theme.dto.Ticker_by_themeDto( tbt.id, tbt.th_code, tbt.ticker_code,tbt.ticker_name) " +
            " from  ticker_by_theme tbt " +
            " where tbt.th_code = :th_code" )
    List<Ticker_by_themeDto> findByTicker_by_theme(@Param("th_code") String th_code);
}
