package com.elstock.ticker.controller;

import com.elstock.ticker.dto.TickerSearchDto;
import com.elstock.ticker.entity.Market;
import lombok.RequiredArgsConstructor;
import com.elstock.ticker.service.TickerSearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class TickerSearchController {

    private final TickerSearchService tickerSearchService;

    @GetMapping(value = {"/search/lists", "/search/lists/{page}"})
    public String TickerSearchManage(TickerSearchDto dto, @PathVariable("page") Optional<Integer> page, Model model){

        System.out.println("TickerSearchController");

        // dto 검색 조건
        // page) 조회할 페이지 번호로써, 옵션 사항(없는 경우 0으로 대체)
        // model) 데이터 저장용 모델 객체

        int pageSize = 3 ;
        int pageNumber = page.isPresent() ? page.get() : 0 ;

        Pageable pageable = PageRequest.of(pageNumber, pageSize) ;

        Page<Market> tickers = this.tickerSearchService.getTickerPage(dto, pageable) ;

        model.addAttribute("tickers", tickers) ;
        model.addAttribute("searchDto", dto) ; // for 검색 조건 유지
        model.addAttribute("maxPage", 5) ; // 하단에 보여줄 페이지 번호의 최대 개수

        System.out.println("tickers : " + tickers.toString());
        System.out.println("dto : " + dto);

        return "/partials/_navbar";
    }
}
