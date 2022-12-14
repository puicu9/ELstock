package com.elstock.theme.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.Entity;
import javax.persistence.Id;

@Getter @Setter @ToString
@Entity(name = "ticker_by_theme")
public class Ticker_by_theme {

    @Id
    private Long id;

    private String th_code;
    private String ticker_code;
    private String ticker_name;
}
