package com.elstock.main.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping(value="/")
public class MainController {

    @GetMapping(value = "/")
    public String main(){
        return "main.html";
    }

}
