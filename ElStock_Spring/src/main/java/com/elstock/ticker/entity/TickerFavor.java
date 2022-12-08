package com.elstock.ticker.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name="favors")
public class TickerFavor {
    @Id
    @GeneratedValue
    @Column(name="ticker_fav_id")
    private String id ;

    @ManyToOne
    @JoinColumn(name="fav_code") // Favor 의 기본 키 컬럼 이름
    private Favor favor;

    @ManyToOne // 여러 개의 즐겨찾기 상품에 하나의 종목이 담길 수 있음
    @JoinColumn(name="ticker_code") // Market 의 기본 키 컬럼 이름
    private Market market ;


    // 즐겨찾기와 종목을 담을 객체를 생성 합니다.
    public static TickerFavor createTickerFavor(Favor favor, Market ticker) {

        TickerFavor tickerFavor = new TickerFavor();

        tickerFavor.setId(favor.getId());
        tickerFavor.setFavor(favor);
        tickerFavor.setMarket(ticker);

        return tickerFavor;
    }
}
