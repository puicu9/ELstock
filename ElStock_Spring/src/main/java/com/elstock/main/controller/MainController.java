package com.elstock.main.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/test")
public class MainController {

    @GetMapping(value = "/99")
    public String main(){
        return "main.html";
    }



}
