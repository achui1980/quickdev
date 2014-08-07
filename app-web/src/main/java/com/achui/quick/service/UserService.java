package com.achui.quick.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achui.quick.domain.User;
import com.achui.quick.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public void saveUser(User user){
		userRepository.save(user);
	}
}
