package com.maykelmarrero.springboot.finangportal.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class CustomErrorController {
    public String handleException(Exception e, Model model){
        model.addAttribute("error", e.getMessage());
        return "error/error";
    }
}
