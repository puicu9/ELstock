package com.elstock.chart.controller;

import com.elstock.chart.dto.Price;
import com.elstock.chart.service.TeslaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChartController {

    private final TeslaService teslaService;

    @GetMapping(value = "/chart")
    public String test(){
        return "/charts/_chart";
    }


    @GetMapping(value = "/stock/load")
    public ResponseEntity<List<Price>> loadTesla(){

        return new ResponseEntity<List<Price>>(this.teslaService.getTesla(), HttpStatus.OK);
    }
}
