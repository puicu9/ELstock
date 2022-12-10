package com.elstock.ticker.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name ="markets")
@Getter @Setter @ToString
public class Market {

    private LocalDateTime date ;
    private int open;
    private int high;
    private int low;
    private int close;
    private int volumn;
    private Long total;
    private float rate;

    @Id
    @Column(name="ticker_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String ticker_code ;

    private String ticker_name;
}
