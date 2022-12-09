package com.elstock.company.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="companys")
@Getter @Setter @ToString
public class Company {
    @Id
    @Column(name="ticker_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String ticker_code;

    private String company_capitalization;
    private String company_rank;
    private String company_share;
    private String company_value;
    private String company_opinion;
    private String company_targetprice;
    private String company_52weeks_max;
    private String company_52weeks_min;
    private String company_comment;
    private String company_url;

}
