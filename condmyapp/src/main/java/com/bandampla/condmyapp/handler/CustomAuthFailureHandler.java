package com.bandampla.condmyapp.handler;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.bandampla.condmyapp.enums.Status;
import com.bandampla.condmyapp.model.User;
import com.bandampla.condmyapp.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthFailureHandler implements AuthenticationFailureHandler {

    private final UserRepository userRepository;
    private static final int MAX_TENTATIVAS = 5;

    public CustomAuthFailureHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException {

        String username = Optional.ofNullable(
                request.getParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY))
                .orElse("").trim();

        var message = "";

        if (exception instanceof DisabledException) {
            message = "Usuário inativo. Contate o administrador.";
        } else if (exception instanceof LockedException) {
            message = "Usuário bloqueado por excesso de tentativas. Contate o administrador.";
        } else if (exception instanceof BadCredentialsException) {
            Optional<User> opt = userRepository.findByUsername(username);
            if (opt.isPresent()) {
                User user = opt.get();
                if (user.getStatus() == Status.INATIVO) {
                    message = "Usuário inativo. Contate o administrador.";
                } else if (user.getStatus() == Status.BLOQUEADO) {
                    message = "Usuário bloqueado por excesso de tentativas. Contate o administrador.";
                } else {
                    int novasTentativas = user.getTentativasLogin() + 1;
                    user.setTentativasLogin(novasTentativas);

                    if (novasTentativas >= MAX_TENTATIVAS) {
                        user.setStatus(Status.BLOQUEADO);
                        message = "Usuário bloqueado após " + MAX_TENTATIVAS + " tentativas.";
                    } else {
                        int restantes = MAX_TENTATIVAS - novasTentativas;
                        message = "Usuário ou senha inválidos. Restam " + restantes + " tentativas.";
                    }
                    userRepository.save(user);
                }
            } else {
                message = "Usuário ou senha inválidos.";
            }
        } else {
            message = "Falha de autenticação. Tente novamente.";
        }

        String encoded = URLEncoder.encode(message, StandardCharsets.UTF_8);
        response.sendRedirect("/login?error=" + encoded);
    }
}
