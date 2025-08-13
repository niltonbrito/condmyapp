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
		.csrf(csrf -> csrf
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // Salva o token em cookie acessível via JS
				)
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/login")
				.permitAll()
				.anyRequest()
				.authenticated())
		.formLogin(form -> form
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/welcome", true)
				.failureUrl("/login?error=true")
				.permitAll())
		.logout(logout -> logout
				.logoutSuccessUrl("/logout")
				.permitAll())
		.userDetailsService(customUserDetailsService);
		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // necessário para comparar a senha criptografada
	}

}