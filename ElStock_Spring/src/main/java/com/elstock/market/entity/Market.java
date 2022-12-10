package com.elstock.market.entity;

import com.elstock.company.entity.Company;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name ="markets")
@Getter @Setter @ToString
public class Market {
//    @Id
//    @Column(name="market_id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private String market_id; // pk

    private LocalDateTime date ;
    private int open;
    private int high;
    private int low;
    private int close;
    private int volume;
    private Long total;
    private float rate;

    // 연관 관계 맵핑
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ticker_code")
//    private Company company;

    @Id
    @Column(name="ticker_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String ticker_code ;
    private String ticker_name;


}
