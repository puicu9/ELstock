package com.elstock.finance.dto;

import com.elstock.finance.Entity.Finance;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.List;


@Getter @Setter @ToString
public class FinanceDto {

    private String dates;
    private double revenue;
    private double operating_profit;
    private double earnings;
    private double operation_income;
    private double net_profit_rate ;
    private double roe;
    private double debt_ratio	;
    private double quick_ratio	;
    private double reserve_ratio ;
    private double eps;
    private double per ;
    private double bps ;
    private double pbr;

    private String ticker_code ;


    public FinanceDto(String date, double revenue, double operating_profit, double earnings, double operation_income, double net_profit_rate, double roe, double debt_ratio, double quick_ratio, double reserve_ratio, double eps, double per, double bps, double pbr, String ticker_code) {
        this.dates = dates;
        this.revenue = revenue;
        this.operating_profit = operating_profit;
        this.earnings = earnings;
        this.operation_income = operation_income;
        this.net_profit_rate = net_profit_rate;
        this.roe = roe;
        this.debt_ratio = debt_ratio;
        this.quick_ratio = quick_ratio;
        this.reserve_ratio = reserve_ratio;
        this.eps = eps;
        this.per = per;
        this.bps = bps;
        this.pbr = pbr;
        this.ticker_code = ticker_code;
    }

    public FinanceDto() {
    }

    private static ModelMapper modelMapper = new ModelMapper() ;

    public static FinanceDto of(Finance finance) {
        // (Entity) 객체의 정보를 ProductFormDto(Dto)에 맵핑해줍니다.
        return modelMapper.map(finance, FinanceDto.class);
    }
}
