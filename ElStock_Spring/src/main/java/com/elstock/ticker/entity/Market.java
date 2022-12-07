package com.elstock.ticker.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name ="markets")
@Getter @Setter @ToString
public class Market {
//    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date ;
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
