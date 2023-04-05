package com.example.app1.controllers;

import com.example.app1.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginUserController {


    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "userLogin";
    }
}
