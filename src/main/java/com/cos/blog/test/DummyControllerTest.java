package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.enumType.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired //의존성 주입(DI)
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		
		try {
			userRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			return "DB상에 해당 아이디 없음";
		}
	
		return "삭제완료";
	}
	
	
	@Transactional // 함수 종료 시 커밋쳐줌
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		 
		//업데이트 시 DB에서 해당 유저의 정보를 user오브젝트에 넣어준 뒤 웹에서 받아온 값을 오브젝트에 세팅하고 트랜잭션 어노테이션을 사용하면 원하는 데이터만 업데이트 가능 / 영속성컨텍스트에 영속화 해주기 위한 호출
		User user=userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id"+id);
			}
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//더티체킹 영속성 컨텍스트의 1차 캐시에 영속화 된 오브젝트에 변화가 있으면 업데이트 쳐주는것
		return user;
		
	}
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	//페이징 전략
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2,sort="id",direction=Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser= userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
		
	}
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		//유저의 4번을 찾으면 내가 데이트베이스에서 못찾으면 user가 null이 된다.
		//그럼 return null이 되어 프로그램에 문제가 있을 수있다.
		//Optional로 user객체를 감싸서 가져올태니 널 판단 후 return하라.
		
		User user=userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id"+id);
			}
		});
		return user;
	}
	
	@PostMapping("/dummy/join")
	public String join(User user){ //User 오브젝트에 매칭되는 키의 벨류값을 파싱해줌.
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입 완료";
		
	}
}
