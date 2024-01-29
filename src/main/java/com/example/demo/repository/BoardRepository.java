package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Board;
import com.example.demo.entity.Member;

import jakarta.transaction.Transactional;

@Transactional // JPA Query로 Insert , Update, Delete 사용시 선언 (commit 처리) 
public interface BoardRepository extends JpaRepository<Board, Integer> {

	//delete from tbl_board where writer_id = 'user1'
	//작성자 필드를 기준으로 게시물을 삭제하는 메소드 추가
	@Modifying //@Query로 INSERT, UPDATE, DETLETE 작성시 선언
	@Query("delete from Board b where b.writer = :member") //(파라미터는 콜론:으로 처리)
	void deleteWriter(@Param("member") Member member);
}
