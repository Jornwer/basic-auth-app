package com.jornwer.basicauthapp.controller;

import com.jornwer.basicauthapp.dto.LoginDTO;
import com.jornwer.basicauthapp.dto.UserDTO;
import com.jornwer.basicauthapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("invalidCredentials", false);
        model.addAttribute("user", new UserDTO());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(LoginDTO dto, Model model){
        if (!userService.login(dto)) {
            model.addAttribute("invalidCredentials", true);
            return "login";
        }
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("userRegistered", false);
        model.addAttribute("user", new UserDTO());
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute("user") @Valid UserDTO dto, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "signup";
        }
        if (!userService.registerUser(dto)) {
            model.addAttribute("userRegistered", true);
            return "signup";
        }
        return "redirect:/";
    }
}
