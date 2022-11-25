package com.elstock.stockMarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/stockMarket")
public class StockMarketHomeController {
    @GetMapping(value = "/home")
    public String home(){
        return "stockMarket/stockMarketHome";
    }
}
