package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.cos.blog.model.enumType.RoleType;
import com.cos.blog.model.enumType.oAuthType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //user클래스가 mysql에 테이블이 생성됨.
//@DynamicInsert //인서트 시 null값은인서트 안한다.
public class User {

	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length=100)
	private String username;
	
	@Column(nullable = false, length=100)//해쉬를 통해 암호화할것임.
	private String password;
	
	@Column(nullable = false, length=50)
	private String email;
	
	//DB는 roletype라는것이 없음.
	@Enumerated(EnumType.STRING)
	private RoleType role;//enum을 쓰는게 좋다. ex 성별 등 사용자로부터 입력받는 값이 정해져있을 경우 //도메인설정
	
	@Enumerated(EnumType.STRING)
	private oAuthType oauth;//로그인회원구분자
	
	@CreationTimestamp //자바에서 데이터가져와서 넣어줌 
	private Timestamp createDate;
	
}
