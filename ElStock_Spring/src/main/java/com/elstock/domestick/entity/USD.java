package com.elstock.domestick.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter @Setter @ToString
@Entity(name = "usds")
public class USD {
    @Id
    private Long id;
    private String usd_date;
    private String usd_price;
}
