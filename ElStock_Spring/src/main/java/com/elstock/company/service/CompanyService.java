package com.elstock.company.service;

import com.elstock.company.entity.Company;
import com.elstock.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company getCompanyDate(String ticker_code) {
        Company company = this.companyRepository.findByComapanyInfo(ticker_code);

        System.out.println("ccccccccccccccc");


        return company ;

    }
}
