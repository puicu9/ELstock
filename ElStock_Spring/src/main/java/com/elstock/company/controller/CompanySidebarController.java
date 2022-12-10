package com.elstock.company.controller;

import com.elstock.company.dto.CompanyDto;
import com.elstock.company.entity.Company;
import com.elstock.company.repository.CompanySidebarRepository;
import com.elstock.company.service.CompanySidebarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class CompanySidebarController {

//    private final CompanySidebarService companySidebarService ;
//    @GetMapping(value="/sidebar")
//    public String SidebarList(Model model){
//        List<CompanyDto> companyList = this.companySidebarService.findByCompanyList();
//        model.addAttribute("companyList", companyList);
//        return "partials/_sidebar" ;
//    }


    // 성공
    private final CompanySidebarService companySidebarService;
    @GetMapping(value = "/lists")
    public String SelectAll(Model model) {
        List<Company> companyList = companySidebarService.SelectAll();
        model.addAttribute("companyList", companyList);
        return "partials/_sidebar" ;
    }




}
