package com.bandampla.condmyapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.bandampla.condmyapp.service.CustomUserDetailsService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final CustomUserDetailsService customUserDetailsService;

	public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
		this.customUserDetailsService = customUserDetailsService;
	}

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Proteção CSRF com Cookie acessível via JS
            .csrf(csrf -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            )
            // Cabeçalhos de segurança
            .headers(headers -> headers
            	    .frameOptions().sameOrigin()
            	    .contentSecurityPolicy(csp -> csp.policyDirectives(
            	        "default-src 'self';" +
            	        "script-src 'self' 'unsafe-inline' https://cdn.jsdelivr.net https://code.jquery.com https://cdn.datatables.net;" +
            	        "style-src 'self' 'unsafe-inline' https://fonts.googleapis.com https://cdn.jsdelivr.net https://cdn.datatables.net;" +
            	        "font-src 'self' https://fonts.gstatic.com https://cdn.jsdelivr.net;" +
            	        "img-src 'self' data:;" +
            	        "connect-src 'self';" +
            	        "frame-src 'none';"
            	    ))
            	)
            // Controle de sessão
            .sessionManagement(session -> session
                .invalidSessionUrl("/login?expired=true")
                .maximumSessions(1)
                .expiredUrl("/login?expired=true")
                .maxSessionsPreventsLogin(false)
                .and()
                .sessionFixation().migrateSession()
            )
            // Permissões
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login",
                		"/logout",
                		"/css/**",
                		"/js/**",
                		"/webfonts/**",
                		"/img/**",
                		"/favicon.ico",
                		"/").permitAll()
                .anyRequest().authenticated()
            )
            // Login
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/home", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            // Logout com limpeza de cookies
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessHandler((request, response, authentication) -> {
                    clearCookie(response, "JSESSIONID");
                    clearCookie(response, "XSRF-TOKEN");
                    request.getSession().invalidate();
                    response.sendRedirect("/login?logout=true");
                })
                .permitAll()
            )

            // Serviço de autenticação
            .userDetailsService(customUserDetailsService);

        return http.build();
    }

    private void clearCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // necessário para comparar a senha criptografada
	}
}