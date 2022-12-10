package com.elstock.ticker.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name ="markets")
@Getter @Setter @ToString
public class Market {
    @Id
    @Column(name="ticker_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String ticker_code ;

    private LocalDateTime date ;
    private int open;
    private int high;
    private int low;
    private int close;
    private int volumn;
    private Long total;
    private float rate;
    private String ticker_name;
}
