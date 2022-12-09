package com.elstock.market.service;

import com.elstock.market.repository.MarketListRepository;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketListService {

    private final MarketListRepository marketListRepository;

    public List<Tuple> getMarketList() {
        return this.marketListRepository.getMarketLists();
//        return null;
    }

//    @Transactional(readOnly = true)
//    public List<MarketDto> getMarketList(LocalDateTime today) {
//        List<MarketDto> marketDtoList = null ;
//        System.out.println("maretDtoList 추출 시작");
//        marketDtoList = this.marketListRepository.findTickerListByDate(today);
//        System.out.println("maretDtoList 추출 완료");
//        return marketDtoList;
//    }
}
