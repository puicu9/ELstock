package com.elstock.ticker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/ticker")
public class TickerHomeController {
    @GetMapping(value = "/home")
    public String home(){
        return "ticker/interestedTicker";
    }
}
