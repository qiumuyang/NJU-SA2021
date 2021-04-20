package com.sa2021.stulistrest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import springfox.documentation.annotations.ApiIgnore;

@Controller
public class IndexController {

    @ApiIgnore()
    @GetMapping("/")
    public String showApi() {
        return "redirect:swagger-ui.html";
    }

}
