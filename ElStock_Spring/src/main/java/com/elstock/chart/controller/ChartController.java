package com.elstock.chart.controller;

import com.elstock.chart.dto.Price;
import com.elstock.chart.service.ChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChartController {

    private final ChartService chartService;


    @GetMapping(value = "/chart")
    public String test(){
        return "/charts/_chart";
    }


    @GetMapping(value = "/stock/load")
    public ResponseEntity<List<Price>> loadChart(@RequestParam String symbol){

        System.out.println(symbol);
        if(symbol.equals("kakao")){
            return new ResponseEntity<List<Price>>(this.chartService.getKakao(), HttpStatus.OK);
        }else if(symbol.equals("samsung")){
            return new ResponseEntity<List<Price>>(this.chartService.getSamsung(), HttpStatus.OK);
        }else{
            return new ResponseEntity<List<Price>>(this.chartService.getTesla(), HttpStatus.OK);
        }
    }
}
