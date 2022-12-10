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
    @Id
    @Column(name="market_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // pk
    private LocalDateTime date ;
    private int open;
    private int high;
    private int low;
    private int close;
    private int volume;
    private Long total;
    private float rate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticker_code")
    private Company company;

    private String ticker_name;

//     , insertable = false, updatable = false
}
