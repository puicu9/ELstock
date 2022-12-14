package com.elstock.domestick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DomesticChartController {

    @GetMapping(value = "/domestic/chart")
    public String DomesticCHart(){
        return "/domestics/domesticChart";
    }

}
