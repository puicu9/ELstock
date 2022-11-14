package com.elstock.controller;

import com.elstock.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeTestController {
    @GetMapping(value = "/employee")
    public Employee test(){
        Employee bean = new Employee() ;
        bean.setId("yusin") ;
        bean.setName("김유신") ;
        bean.setAge(20) ;
        return bean ;
    }
}
