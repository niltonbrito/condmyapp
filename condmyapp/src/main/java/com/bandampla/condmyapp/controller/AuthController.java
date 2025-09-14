package com.bandampla.condmyapp.controller;

import com.bandampla.condmyapp.dto.UserRequestDTO;
import com.bandampla.condmyapp.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping({"/", "/login"})
    public String login() {
        return "login"; // sem barra inicial para evitar conflito
    }

    @GetMapping("/auth/alterarsenha")
    public String alterarSenhaPage(@ModelAttribute("user") UserRequestDTO dto) {
        return "auth/alterarsenha";
    }

    @PostMapping("/auth/alterarsenha")
    public String alterarSenha(@ModelAttribute UserRequestDTO dto,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               RedirectAttributes redirectAttributes) {
        try {
            authService.alterarSenha(dto, request, response);
            redirectAttributes.addFlashAttribute("message", "Senha alterada com sucesso. Faça login novamente.");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/login"; 
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/auth/alterarsenha";
        }
    }
}
