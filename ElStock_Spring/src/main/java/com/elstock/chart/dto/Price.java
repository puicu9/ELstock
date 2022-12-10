package com.elstock.chart.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Price {

//    {"date":1666791000,"volume":85012500,"high":230.60000610351562,
//            "low":218.1999969482422,"adjclose":224.63999938964844,
//            "close":224.63999938964844,"open":219.39999389648438}

    private Long date;
    private Long volume;
    private Double high;
    private Double low;
    private Double adjclose;
    private Double close;
    private Double open;


}
