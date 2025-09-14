package com.bandampla.condmyapp.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.bandampla.condmyapp.enums.Status;
import com.bandampla.condmyapp.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserRepository userRepository;

    public CustomAuthSuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
        setDefaultTargetUrl("/home");
        setAlwaysUseDefaultTargetUrl(true);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails ud) {
            userRepository.findByUsername(ud.getUsername()).ifPresent(user -> {
                user.setTentativasLogin(0);
                if (user.getStatus() != Status.ATIVO) {
                    user.setStatus(Status.ATIVO);
                }
                userRepository.save(user);

                try {
                    if (user.isTrocarSenhaProximoLogin()) {
                        response.sendRedirect("/auth/alterarsenha");
                        return;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
