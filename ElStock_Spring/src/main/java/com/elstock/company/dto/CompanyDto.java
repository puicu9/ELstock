package com.elstock.company.dto;

import com.elstock.company.entity.Company;
import com.elstock.finance.dto.FinanceDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import javax.persistence.*;


public class CompanyDto {

    private String ticker_code;
    private String ticker_name;

    
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

    public CompanyDto(String ticker_code, String ticker_name, String company_capitalization, String company_rank, String company_share, String company_value, String company_opinion, String company_targetprice, String company_52weeks_max, String company_52weeks_min, String company_comment, String company_url) {
        this.ticker_code = ticker_code;
        this.ticker_name = ticker_name;
        this.company_capitalization = company_capitalization;
        this.company_rank = company_rank;
        this.company_share = company_share;
        this.company_value = company_value;
        this.company_opinion = company_opinion;
        this.company_targetprice = company_targetprice;
        this.company_52weeks_max = company_52weeks_max;
        this.company_52weeks_min = company_52weeks_min;
        this.company_comment = company_comment;
        this.company_url = company_url;
    }

    public CompanyDto() {
    }

    private static ModelMapper modelMapper = new ModelMapper() ;

    public static CompanyDto of(Company company){
        return modelMapper.map(company, CompanyDto.class);
    }
}
