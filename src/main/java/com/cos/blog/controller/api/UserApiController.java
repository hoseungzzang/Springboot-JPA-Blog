package com.cos.blog.controller.api;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		userService.join(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

	}
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user) { //json데이터 받을때 리퀘스트바디써야함
		userService.userUpdate(user);
		//이 타이밍에 DB값은 변경이 끝났지만, 세션값은 그대로임 그래서 직접 세션값은 변경해줘야함.
		//세션등록
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

	}
	/*
	 * //정통적인 방식의 로그인
	 * 
	 * @PostMapping("/api/user/login") public ResponseDto<Integer>
	 * login(@RequestBody User user , HttpSession session;) {
	 * 
	 * 
	 * User principal = userService.login(user); if(principal != null){
	 * session.setAttribute("principal", principal); } return new
	 * ResponseDto<Integer>(HttpStatus.OK.value(),1);
	 * 
	 * }
	 */
}
