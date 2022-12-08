package com.elstock.ticker.repository;

import com.elstock.ticker.dto.TickerFavDto;
import com.elstock.ticker.entity.Favor;
import com.elstock.ticker.entity.TickerFavor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TickerFavRepository extends JpaRepository<Favor, String> {
// 장바구니에 들어갈 티커를 저장하거나 조회합니다.
TickerFavor findByFavCodeAndTickerCode(String fav_code, String ticker_code);
    @Query(" select new com.elstock.ticker.dto.TickerFavDto(f.fav_code, f.fav_name) " +
            " from Favor f " +
            " where ")
    List<TickerFavDto> findByFavDtoList(@Param("fav_code") String fav_code);
}
