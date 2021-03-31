package com.sa2021.stulist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/err")
    public String showErr(Model m) {
        return "error";
    }
}
