package com.bandampla.condmyapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.bandampla.condmyapp.enums.Status;
import com.bandampla.condmyapp.model.User;
import com.bandampla.condmyapp.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
	public List<User> findAllUser() {
		return userRepository.findAll();
	}
	
    public void create(User user, String confirmPassword, Model model) {
    	if (user.getUserGroup() == null) {
            throw new IllegalArgumentException("O grupo do usuário é obrigatório");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }
}
