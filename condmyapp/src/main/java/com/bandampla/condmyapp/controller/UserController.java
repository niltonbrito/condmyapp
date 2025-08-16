package com.bandampla.condmyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bandampla.condmyapp.model.User;
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
    
    @GetMapping("/user/new")
    public String news(Model model) {
        model.addAttribute("user", new User());
        return "user/new";
    }
    

    @PostMapping("/user/create")
    public String create(User user, String confirmPassword, Model model) {

    	userService.create(user, confirmPassword, model);
        return "redirect:/user";
    }
}
