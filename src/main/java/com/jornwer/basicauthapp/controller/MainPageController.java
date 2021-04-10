package com.jornwer.basicauthapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainPageController {
    @GetMapping
    public String loadMainPage() {
        return "index";
    }
}
