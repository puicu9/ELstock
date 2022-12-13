package com.elstock.market.controller;

import com.elstock.market.dto.MarketDto;
import com.elstock.market.service.MarketListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class MarketListController {

    private final MarketListService marketListService;

    @GetMapping(value = "/marketList")
    public String MarketList (MarketDto marketDto, Model model){
        System.out.println("MarketListController");




//        LocalDateTime dateTime = LocalDateTime.now();
//        String dateTimeString = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00"));

//        String today = "2022-12-09" ;
        // 매개변수 어떻게를 들어가고 타입이 뭔지
//        LocalDateTime today = LocalDateTime.parse(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00")));

//        List<MarketDto> marketDtoList = this.marketListService.getMarketList(today);
//        model.addAttribute("marketDtoList", marketDtoList);
//
//        System.out.println("marketDtoList 추출");
//        System.out.println(marketDtoList.toString());


//        List<Tuple> lists = this.marketListService.getMarketList();

//        model.addAttribute("lists", lists);




        return "partials/_sidebar";
    }

}
