package com.bandampla.condmyapp.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bandampla.condmyapp.model.User;
import com.bandampla.condmyapp.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService  {

	private UserRepository userRepository;
	
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
    @Cacheable(value = "users", key = "#username")
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    User user = userRepository.findByUsername(username)
	        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
	    return new CustomUserDetails(user);
	}
}
