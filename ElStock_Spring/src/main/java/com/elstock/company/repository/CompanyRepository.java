package com.elstock.company.repository;

import com.elstock.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface CompanyRepository extends JpaRepository<Company, String> {

        @Query(value=" select c from Company c " +
            " where c.ticker_code = :ticker_code")
        Company findByComapanyInfo(@Param("ticker_code") String ticker_code);

}
