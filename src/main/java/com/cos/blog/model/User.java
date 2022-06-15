package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //user클래스가 mysql에 테이블이 생성됨.
public class User {

	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length=30)
	private String username;
	
	@Column(nullable = false, length=100)//해쉬를 통해 암호화할것임.
	private String password;
	
	@Column(nullable = false, length=50)
	private String email;
	
	@ColumnDefault("'user'")
	private String role;//enum을 쓰는게 좋다. ex 성별 등 사용자로부터 입력받는 값이 정해져있을 경우 //도메인설정
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
