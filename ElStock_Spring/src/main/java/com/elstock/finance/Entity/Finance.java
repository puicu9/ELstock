package com.elstock.finance.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="fss")
@Getter @Setter @ToString
public class Finance {

    private String dates;
    private double revenue;
    private double operating_profit;
    private double earnings;
    private double operation_income;
    private double net_profit_rate	;
    private double roe;
    private double debt_ratio	;
    private double quick_ratio	;
    private double reserve_ratio ;
    private double eps;
    private double per ;
    private double bps ;
    private double pbr;

    @Id
    @Column(name="ticker_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String ticker_code ;


}
