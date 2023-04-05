package com.example.app1.controllers;

import com.example.app1.dto.userDTO;
import com.example.app1.entities.User;
import com.example.app1.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@RequiredArgsConstructor
@Slf4j


@Controller
public class CreateUserController {

    @Autowired
    private UserService registrationService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        System.out.println("UserCreate");
        return "userCreate";
    }

    @PostMapping("/register")
    public RedirectView registerUser(@ModelAttribute("Users") userDTO userDTO) {
        System.out.println("Post запрос получен");
        registrationService.register(userDTO.getName(), userDTO.getUsername(), userDTO.getPassword());
        return new RedirectView("/login");
    }




}