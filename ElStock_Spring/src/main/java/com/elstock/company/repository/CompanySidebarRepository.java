package com.elstock.company.repository;

import com.elstock.company.dto.CompanyDto;
import com.elstock.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanySidebarRepository extends JpaRepository<Company, String> {
//    List<CompanyDto> findAllOrderByCompanyRankDesc();

//    @Query(" select c.ticker_code, c.ticker_name, c.company_rank from Company c ")
//    public List<CompanySidebarDto> findBySidebarList() {
//
//    }
}
