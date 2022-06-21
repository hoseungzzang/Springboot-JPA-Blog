package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //user클래스가 mysql에 테이블이 생성됨.
public class Board {
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length=100)
	private String title;
	
	@Lob//대용량데이터
	private String content; //섬머노트 라이브러리 
	
	private int count;
	
	//단건이기 때문에 기본 페치는 데이터를 무조건 가져오는걸로 설정 (이걸전략)
	@ManyToOne(fetch = FetchType.EAGER) //many = Board, User=One 한명의 유저가 여러개의 게시글을 사용할 수 있다. <-> OneToOne 한명
	@JoinColumn(name="userId")
	private User user; //db는 오브젝트저장 안되지만 자바는 저장가능
	//many = Board, User=One 한명의 유저가 여러개의 게시글을 사용할 수 있다. <-> OneToOne 한명
	//의 유저가 한개의 게시글만 쓸 수 있다.
	
	//밑의 데이터는 수많은 데이터가 있을 수 있으므로 화면 표시 시 보이게할거면 이걸전략, 기본 전략(패치)가 레이지 전략임.
	@OneToMany(mappedBy="board",fetch = FetchType.EAGER) //mappedBy가 붙어있으면 FK가 아니니 DB에 컬럼을 만들지 말라는 뜻임.
	@JsonIgnoreProperties({"board"})
	@OrderBy("id desc")
	private List<Reply> replys;
	//조인컬럼을 사용하게 되면 DB상에 컬럼이추가되는데 리스트형식은 여러개를 가져오기 때문에 DB에 데이터를 넣게되면 원자성 어긋남
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
