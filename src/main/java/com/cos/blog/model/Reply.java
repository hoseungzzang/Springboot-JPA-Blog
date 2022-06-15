package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

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
public class Reply {

	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false,length=200)//대용량데이터
	private String content; //섬머노트 라이브러리 

	@ManyToOne //many = Board, User=One 한명의 유저가 여러개의 게시글을 사용할 수 있다. <-> OneToOne 한명
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne //many = Board, User=One 한명의 유저가 여러개의 게시글을 사용할 수 있다. <-> OneToOne 한명
	@JoinColumn(name="userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
}
