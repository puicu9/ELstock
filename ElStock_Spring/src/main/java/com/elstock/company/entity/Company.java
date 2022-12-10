package com.elstock.company.entity;

import com.elstock.market.entity.Market;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.annotations.Mapper;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="companys")
@Getter @Setter @ToString
public class Company {
    //@Id
    //@Column(name="ticker_id")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id ; // pk
//    @JoinColumn(name="ticker_code")
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<Market> market = new ArrayList<>() ;
    @Id
    private String ticker_code;
    private String ticker_name;

    private String company_capitalization;
    private int company_rank;
    private String company_share;
    private String company_value;
    private String company_opinion;
    private String company_targetprice;
    private String company_52weeks_max;
    private String company_52weeks_min;
    private String company_comment;
    private String company_url;

}
