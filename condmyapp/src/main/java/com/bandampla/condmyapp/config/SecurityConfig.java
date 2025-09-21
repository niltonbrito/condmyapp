package com.bandampla.condmyapp.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.bandampla.condmyapp.handler.CustomAuthFailureHandler;
import com.bandampla.condmyapp.handler.CustomAuthSuccessHandler;
import com.bandampla.condmyapp.service.CustomUserDetailsService;

import jakarta.servlet.http.Cookie;

@Configuration
@EnableWebSecurity
@EnableCaching
public class SecurityConfig {

	private final CustomUserDetailsService customUserDetailsService;
	private final CustomAuthFailureHandler failureHandler;
	private final CustomAuthSuccessHandler successHandler;

	public SecurityConfig(CustomUserDetailsService customUserDetailsService,
			CustomAuthFailureHandler failureHandler,
			CustomAuthSuccessHandler successHandler) {
		this.customUserDetailsService = customUserDetailsService;
		this.failureHandler = failureHandler;
		this.successHandler = successHandler;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		// Se quiser manter CSRF, pode deixar. Caso contrário use csrf(csrf -> csrf.disable()).
		.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))

		.headers(headers -> headers
				.frameOptions(frame -> frame.sameOrigin())
				.cacheControl(cache -> {})
				.contentSecurityPolicy(csp -> csp.policyDirectives(
						"default-src 'self'; " +
								"script-src 'self' 'unsafe-inline' https://cdn.jsdelivr.net https://code.jquery.com https://cdn.datatables.net; " +
								"style-src 'self' 'unsafe-inline' https://fonts.googleapis.com https://cdn.jsdelivr.net https://cdn.datatables.net; " +
								"font-src 'self' https://fonts.gstatic.com https://cdn.jsdelivr.net; " +
								"img-src 'self' data:; " +
								"connect-src 'self'; " +
								"frame-src 'none';"
						))
				)
		.sessionManagement(session -> session
				// Garante que uma nova sessão só é criada quando o usuário faz login
				.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
				.invalidSessionUrl("/login?logout=true")
				.maximumSessions(1)
				.maxSessionsPreventsLogin(false)
				.expiredUrl("/login?expired=true")
				)
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/login", "/logout", "/css/**", "/js/**", "/webfonts/**", "/img/**", "/favicon.ico").permitAll()
				.anyRequest().authenticated()
				)
		.formLogin(form -> form
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.successHandler(successHandler)
				.failureHandler(failureHandler)
				.permitAll()
				)
		.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessHandler((request, response, authentication) -> {
					// Invalida sessão no servidor
					if (request.getSession(false) != null) {
						request.getSession().invalidate();
					}
					// Limpa contexto de segurança
					SecurityContextHolder.clearContext();

					// Deleta cookies
					Cookie jsessionCookie = new Cookie("JSESSIONID", null);
					jsessionCookie.setPath("/");
					jsessionCookie.setMaxAge(0);
					response.addCookie(jsessionCookie);

					Cookie xsrfCookie = new Cookie("XSRF-TOKEN", null);
					xsrfCookie.setPath("/");
					xsrfCookie.setMaxAge(0);
					response.addCookie(xsrfCookie);

					// Redireciona para login
					response.sendRedirect("/login?logout");
				})
				.clearAuthentication(true)
				.deleteCookies("JSESSIONID", "XSRF-TOKEN")
				)
		.userDetailsService(customUserDetailsService);

		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
