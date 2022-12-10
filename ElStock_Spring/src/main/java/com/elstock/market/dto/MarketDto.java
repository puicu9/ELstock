package com.elstock.market.dto;

import com.elstock.market.entity.Market;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter @Setter
public class MarketDto {
    private LocalDateTime date ;
    private int open;
    private int high;
    private int low;
    private int close;
    private int volume;
    private Long total;
    private float rate;

    private String ticker_code ;
    private String ticker_name;

    private int company_rank ;
//    select new com.elstock.market.dto.MarketDto(
//    m.open, m.high, m.low, m.close, m.volume, m.total, m.rate, m.ticker_code, m.ticker_name, c.company_rank

    @QueryProjection
    public MarketDto(LocalDateTime date, int open, int high, int low, int close, int volume, Long total, float rate, String ticker_code, String ticker_name, int company_rank) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.total = total;
        this.rate = rate;
        this.ticker_code = ticker_code;
        this.ticker_name = ticker_name;
        this.company_rank = company_rank;
    }


    private static ModelMapper modelMapper = new ModelMapper();

    public static MarketDto of(Market market){
        return modelMapper.map(market, MarketDto.class);
    }
}
