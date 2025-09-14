package com.bandampla.condmyapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bandampla.condmyapp.dto.UserRequestDTO;
import com.bandampla.condmyapp.dto.UserResponseDTO;
import com.bandampla.condmyapp.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /** LISTAGEM DE USUÁRIOS */
    @GetMapping
    public String index(Model model,
                        @ModelAttribute("success") String success,
                        @ModelAttribute("error") String error) {
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("successMessage", success);
        model.addAttribute("errorMessage", error);
        return "user/index";
    }

    /** PÁGINA DE CADASTRO */
    @GetMapping("/new")
    public String news(Model model) {
        if(!model.containsAttribute("user")) {
            model.addAttribute("user", new UserRequestDTO());
        }
        return "user/new";
    }

    /** CRIAR USUÁRIO */
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("user") UserRequestDTO userDTO,
                         RedirectAttributes redirectAttributes) {
        try {
            userService.create(userDTO);
            redirectAttributes.addFlashAttribute("message", "Usuário cadastrado com sucesso!");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");
            redirectAttributes.addFlashAttribute("user", userDTO); // mantém os dados no formulário
            return "redirect:/user/new";
        }
        return "redirect:/user";
    }

    /** PÁGINA DE EDIÇÃO */
    @GetMapping("/{id:[0-9]+}")
    public String edit(@PathVariable Long id, Model model) {
        try {
            UserResponseDTO userDTO = userService.edit(id);
            model.addAttribute("user", userDTO);
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "redirect:/user";
        }
        return "user/edit";
    }

    /** ALTERAR USUÁRIO */
    @PostMapping("/{id:[0-9]+}/alter")
    public String alter(@PathVariable Long id,
                        @Valid @ModelAttribute("user") UserRequestDTO userDTO,
                        @RequestParam("confirmPassword") String confirmPassword,
                        HttpServletRequest request,
                        RedirectAttributes redirectAttributes) {
        try {
            userService.update(id, userDTO, confirmPassword, request);
            redirectAttributes.addFlashAttribute("message", "Usuário alterado com sucesso!");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");
            redirectAttributes.addFlashAttribute("user", userDTO);
            return "redirect:/user/" + id;
        }
        return "redirect:/user";
    }

    /** DELETAR USUÁRIO */
    @GetMapping("/{id:[0-9]+}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            userService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Usuário deletado com sucesso!");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");

        }
        return "redirect:/user";
    }

    /** DESBLOQUEAR USUÁRIO */
    @GetMapping("/{id:[0-9]+}/desbloquear")
    public String unlockUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            userService.unlockUser(id);
            redirectAttributes.addFlashAttribute("message", "Usuário desbloqueado com sucesso!");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");
        }
        return "redirect:/user";
    }
}
