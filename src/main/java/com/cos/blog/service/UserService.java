package com.cos.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public int join(User user) {
		
		try {
			 userRepository.save(user);
			 return 1;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("오류");
		}
		return -1;
		
	}
}
