package com.elstock.domestick.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter @Setter @ToString
@Entity(name = "jpys")
public class JPY {

    @Id
    private Long id;

    private String jpy_date;
    private String jpy_price;
}
