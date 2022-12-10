package com.elstock.company.service;

import com.elstock.company.Mapper.CompanyMapperInterface;
import com.elstock.company.dto.CompanyDto;
import com.elstock.company.entity.Company;
import com.elstock.company.repository.CompanySidebarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanySidebarService {

    private final CompanyMapperInterface companyMapperInterface ;

    public List<Company> SelectAll() {
        return companyMapperInterface.SelectAll();
    }


//    private final CompanySidebarRepository companySidebarRepository;
//    @Transactional(readOnly = true)
//    public List<CompanyDto> findByCompanyList() {
//        List<CompanyDto> companySidebarDtoList = null ;
//        System.out.println("ddddddddd");
//        companySidebarDtoList = this.companySidebarRepository.findAllOrderByCompanyRankDesc();
//        System.out.println("eeeeeeeee");
//
//        return companySidebarDtoList;
//    }
}
