package com.bandampla.condmyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bandampla.condmyapp.service.UserService;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class UserController {

    @Autowired
    private UserService userService;
  

    @GetMapping("/user")
    public String index(Model model, HttpServletRequest request) {
        // Carrega todos os usuários do banco
        model.addAttribute("users", userService.findAllUser());
        return "user/index"; // nome do template
    }
}
