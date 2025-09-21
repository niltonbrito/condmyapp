package com.bandampla.condmyapp.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bandampla.condmyapp.dto.UserRequestDTO;
import com.bandampla.condmyapp.model.User;
import com.bandampla.condmyapp.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class AuthService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	private static final String STRONG_REGEX = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$";

	@Transactional
	public void alterarSenha(UserRequestDTO dto,
			HttpServletRequest request,
			HttpServletResponse response) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || auth.getName() == null) throw new RuntimeException("Usuário não identificado. Faça login novamente.");

		Optional<User> opt = userRepository.findByUsername(auth.getName());
		if (opt.isEmpty()) throw new RuntimeException("Usuário não encontrado. Faça login novamente.");

		User user = opt.get();

		String newPassword = nullSafe(dto.getPassword());
		String confirmPassword = nullSafe(dto.getConfirmPassword());

		if (newPassword.isBlank() || confirmPassword.isBlank()) throw new RuntimeException("Preencha a nova senha e a confirmação.");

		if (!newPassword.equals(confirmPassword)) throw new RuntimeException("As senhas não coincidem.");

		if (user.getPassword() != null && passwordEncoder.matches(newPassword, user.getPassword())) throw new RuntimeException("A nova senha não pode ser igual à senha atual.");

		if (!newPassword.matches(STRONG_REGEX)) throw new RuntimeException("Senha fraca: mínimo 8 caracteres, 1 maiúscula, 1 número e 1 caractere especial.");

		if (containsUserInfoOrBadPatterns(newPassword, user)) throw new RuntimeException("A senha não pode conter partes do nome, username, data de nascimento ou sequências numéricas óbvias.");

		user.setPassword(passwordEncoder.encode(newPassword));
		user.setTrocarSenhaProximoLogin(false);
		user.setTentativasLogin(0);
		userRepository.save(user);
		HttpSession session = request.getSession(false);
		if (session != null) session.invalidate();
		SecurityContextHolder.clearContext();        
	}

	private String nullSafe(String s) {
		return s == null ? "" : s.trim();
	}

	private boolean containsUserInfoOrBadPatterns(String password, User user) {
		String lower = password.toLowerCase(Locale.ROOT);

		if (user.getFirstName() != null) {
			for (String token : user.getFirstName().split("\\s+")) {
				if (token.length() >= 3 && lower.contains(token.toLowerCase())) return true;
			}
		}
		if (user.getLastName() != null) {
			for (String token : user.getLastName().split("\\s+")) {
				if (token.length() >= 3 && lower.contains(token.toLowerCase())) return true;
			}
		}

		LocalDate bd = user.getBirthDate();
		if (bd != null) {
			String[] patterns = {"ddMMyyyy", "yyyyMMdd", "ddMMyy", "yyyy", "MMyyyy", "MMyy"};
			for (String p : patterns) {
				try {
					String s = new SimpleDateFormat(p).format(bd);
					if (s != null && s.length() >= 4 && lower.contains(s.toLowerCase())) return true;
				} catch (Exception ignored) {}
			}
		}

		if (hasSequentialOrRepeatedNumbers(password)) return true;

		if (user.getCpf() != null) {
			String cpfDigits = user.getCpf().replaceAll("\\D", "");
			if (cpfDigits.length() >= 4) {
				for (int len = 4; len <= Math.min(6, cpfDigits.length()); len++) {
					if (lower.contains(cpfDigits.substring(0, len))) return true;
				}
			}
		}
		return false;
	}

	private boolean hasSequentialOrRepeatedNumbers(String password) {
		String digits = password.replaceAll("\\D", "");
		if (digits.length() < 4) return false;

		for (int i = 0; i <= digits.length() - 4; i++) {
			for (int len = 4; i + len <= digits.length(); len++) {
				String sub = digits.substring(i, i + len);
				if (isAllSame(sub) || isSequential(sub)) return true;
			}
		}
		if (password.contains("1234") || password.contains("2345") || password.contains("0123") || password.contains("9876"))
			return true;

		return false;
	}

	private boolean isAllSame(String s) {
		char c = s.charAt(0);
		for (int i = 1; i < s.length(); i++) if (s.charAt(i) != c) return false;
		return true;
	}

	private boolean isSequential(String s) {
		boolean up = true, down = true;
		for (int i = 1; i < s.length(); i++) {
			int diff = (s.charAt(i) - '0') - (s.charAt(i - 1) - '0');
			if (diff != 1) up = false;
			if (diff != -1) down = false;
			if (!up && !down) return false;
		}
		return up || down;
	}
}
