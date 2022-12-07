package com.elstock.chart.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor //build쓸 때 필요함
public class Tesla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long date;
    private Long volume;
    private Double high;
    private Double low;
    private Double adjclose;
    private Double close;
    private Double open;

    public Tesla() {

    }
}
