package com.elstock.ticker.repository;

import com.elstock.ticker.entity.Favor;
import com.elstock.ticker.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavorRepository extends JpaRepository<Favor,String > {
    Favor findByMemberId(String memberEmail);

}
