package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

//DAO
//자동으로 bean으로 등록이 된다.
public interface UserRepository extends JpaRepository<User,Integer> {

	//JPA Naming전략
	//select * from user where username = ? and password=?; 실행됨
	//User findByUsernameAndPassword(String username, String passowrd);
	
	/*
	 * @Query(value =
	 * "select * from user where username = ? and password=?",nativeQuery =true)
	 * User login(String username, String password);
	 */
	Optional<User> findByUsername(String username);
 }
