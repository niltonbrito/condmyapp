package com.bandampla.condmyapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bandampla.condmyapp.model.User;
import com.bandampla.condmyapp.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAllUser() {
		return userRepository.findAll();
	}
}
