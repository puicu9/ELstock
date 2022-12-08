package com.elstock.ticker.repository;

import com.elstock.ticker.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository<Market,String > {
}
