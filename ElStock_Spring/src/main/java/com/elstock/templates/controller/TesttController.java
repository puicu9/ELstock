package com.elstock.templates.controller;

import com.elstock.templates.entity.testEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesttController {
    @GetMapping(value = "/test123")
    public testEntity test(){
        testEntity bean = new testEntity() ;
        bean.setId("yusin") ;
        bean.setName("김유신") ;
        bean.setAge(20) ;
        return bean ;
    }
}
