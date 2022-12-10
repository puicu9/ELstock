package com.elstock.finance.controller;

import com.elstock.company.entity.Company;
import com.elstock.company.service.CompanyService;
import com.elstock.finance.dto.FinanceDto;
import com.elstock.finance.service.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class FinanceController {

    private final FinanceService financeService ;
    private final CompanyService companyService ;

    @GetMapping(value="finance/{ticker_code}")
    public String FinanceDetail(@PathVariable("ticker_code") String ticker_code, Model model){
        // finance 정보 보내기
        List<FinanceDto> financeDtoList = this.financeService.getFinanceList(ticker_code);

//        System.out.println("financeList.toString()");
//        System.out.println(financeDtoList.toString());
        model.addAttribute("financeDtoList", financeDtoList) ;

        // company_기본정보 보내기
        Company companyDto = this.companyService.getCompanyDate(ticker_code);
        model.addAttribute("companyDto", companyDto) ;

        return "finance/financeDetailForm" ;
    }

}
