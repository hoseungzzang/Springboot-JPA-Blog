package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	//스프링이 로그인 요청을 가로챌때 가로챈 변수들을 가로채는데 
	//password 부분은 알아서 처리함.
	//username이 DB에 있는지 확인해줘야함.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당 사용자를 찾울 수 없습니다."+username);
				});
		
		return new PrincipalDetail(principal); //시큐리티의 세션에 유저정보가 저장이됨
	}
	
}
