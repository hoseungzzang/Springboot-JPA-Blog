package com.cos.blog.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply,Integer>{

	@Modifying
	@Query(value="INSERT INTO reply(userId, boardId, content, createDate) VALUES(?1,?2,?3,now())"
			,nativeQuery=true) //이렇게 호출하면 영속화해서 값을 집어넣을 필요가 없다.
	public int mSave(int userId, int boardId, String content);
	//db에서 인서트나 업데이트가 되었을 경우 리턴값을 트랜잭션이 수행된 행의 개수를 리턴해준다.
}
