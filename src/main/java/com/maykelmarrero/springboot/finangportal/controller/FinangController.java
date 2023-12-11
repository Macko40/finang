package com.maykelmarrero.springboot.finangportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/finangportal")
public class FinangController {

    @GetMapping("/admin")
    public String administration(){
        return "/finang/main-administration";
    }
}
