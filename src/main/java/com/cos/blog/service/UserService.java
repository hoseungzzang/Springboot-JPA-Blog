package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Transactional
	public void  join(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
	    userRepository.save(user);
	}
	
	@Transactional
	public void  userUpdate(User user) {
		//수정시에는 영속성 컨텍스트에 해당 오브젝트를 영속화 시키고, 영속화 된 오브젝트를 수정하는것이 가장 좋다.
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원수정 실패: 아이디를 찾을 수 없습니다.");
		});
		
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		//서비스 종료 시 커밋 자동으로 됨.
		//persistance란 이름으로 영속화된 USER오브젝트에 변화가 감지되면 더티체킹이 되어 update문 날림.
	}
	/*
	@Transactional(readOnly=true) // select 시 트랜잭션 시작, 서비스 종료 시 트랜잭션 종료(정합성)
	public User  login(User user) {
			 return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
	}*/
}
