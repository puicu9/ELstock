package com.elstock.finance.service;

import com.elstock.finance.dto.FinanceDto;
import com.elstock.finance.repository.FinanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinanceService {
    private final FinanceRepository financeRepository ;

    @Transactional(readOnly = true)
    public List<FinanceDto> getFinanceList(String ticker_code){
        // 0개 들어있음
        List<FinanceDto> financeDtoList = new ArrayList<>() ;
        System.out.println("aaaaaaaaaaaaaaaaaa");

        // db연동하여 가져옴
        financeDtoList = this.financeRepository.findByFinanceDetail(ticker_code);
        System.out.println("bbbbbbbbbbbbbbbbbb");

        return financeDtoList ;
    }



}
