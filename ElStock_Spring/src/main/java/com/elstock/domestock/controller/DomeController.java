package com.elstock.domestock.controller;

import com.elstock.chart.service.ChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DomeController {

    private final ChartService teslaService;

    @GetMapping(value = "/domestic")
    public String test(){
        return "/domestics/_domestic";
    }
}
