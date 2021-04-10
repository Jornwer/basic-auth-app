package com.jornwer.basicauthapp.controller;

import com.jornwer.basicauthapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class MainPageController {
    private final UserService userService;

    @GetMapping
    public String loadMainPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }
}
