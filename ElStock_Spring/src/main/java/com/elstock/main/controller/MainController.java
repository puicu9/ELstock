package com.elstock.main.controller;

import com.elstock.company.entity.Company;
import com.elstock.company.service.CompanySidebarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
//@RequestMapping(value="/")
public class MainController {

//    private final CompanySidebarService companySidebarService;
//
//
//    public MainController(CompanySidebarService companySidebarService) {
//        this.companySidebarService = companySidebarService;
//    }
//
//    @GetMapping(value = "/")
//    public String SelectAll(Model model) {
//        List<Company> companyList = companySidebarService.SelectAll();
//        model.addAttribute("companyList", companyList);
//
//        return "partials/_sidebar" ;
//    }

//    @GetMapping(value = "/")
//    public String main(){
//        return "main.html";
//    }


}
