package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 ->응답
@RestController
public class httpControllerTest {
	private static final String TAG="httpControllerTest:";
	
	@GetMapping("/http/get")
	public String getTest(Member m) {
		System.out.println("getter");
		return "get요청 "+m.getId()+","+m.getUsername();
	}
	@PostMapping("/http/post") 
	public String postTest(@RequestBody Member m) { //메세지컨버터에 설정한 데이터타입으로 데이터가 들어오면 스프링부트에서 알아서 파싱해준다. 해당 역할을 메세지컨버터가 해줌
		
		return "post요청 "+m.getId()+","+m.getUsername();
	}
	@PutMapping("/http/put")
	public String putTest() {
		return "put요청";
	}
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
	}
