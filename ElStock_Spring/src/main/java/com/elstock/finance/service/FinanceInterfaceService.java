package com.elstock.finance.service;

import com.elstock.finance.Entity.Finance;
import com.elstock.finance.dto.FinanceDto;

import java.util.Collection;

public interface FinanceInterfaceService {

    FinanceDto create(FinanceDto dto);
    Collection<FinanceDto> readAll();
    FinanceDto read(String ticker_code);
    boolean update(FinanceDto dto, String ticker_code);
    boolean delete(String ticker_code);

}
