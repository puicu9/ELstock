package com.elstock.domestock.controller;

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
public class DomeController {

    private final TeslaService teslaService;

    @GetMapping(value = "/domestic")
    public String test(){
        return "/domestics/_domestic";
    }
}
