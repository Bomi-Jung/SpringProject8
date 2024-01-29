package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.entity.Board;
import com.example.demo.entity.Member;

@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	BoardRepository repository;
	
	@Test
	public void 게시물등록() {
		//회원 엔티티 생성
		Member member = Member.builder().id("user1").build();
		//아이디 하나로 여러 게시물 등록
		List<Board> list = new ArrayList<>();
		//작성자는 현재 존재하는 사용자 아이디를 사용해야함 
		list.add(new Board(0,"1번글", "내용입니다", member));
		list.add(new Board(0,"2번글","내용입니다", member));
		repository.saveAll(list);
	}
	
	@Test
	public void 게시물조회() {
		//쿼리가 내부적으로 join 처리됨
		Optional<Board> optional = repository.findById(2);
		Board board = optional.get();
		System.out.println(board); // 회원정보가 함께 출력됨 
	}
	
//	@Test
//	public void 게시물30개추가() {
//		
//		for(int i =1; i<= 30; i++) {
//		Board board = Board.builder().title(i + "번글")
//				.content("안녕하세요").writer("둘리").build();
//		
//		repository.save(board);
//	
//		}
//	}
	
	@Test
	public void 페이지테스트() {
		
		//1페이지에 10개 게시글
		Pageable pageable = PageRequest.of(0, 10);
		
		Page<Board> result = repository.findAll(pageable);
		//게시물 리스트 + 페이지 정보
		System.out.println(result);
		
		List<Board> list = result.getContent();
		//게시물 리스트 
		System.err.println(list);
	}
	
	@Test
	public void 정렬조건추가하기() {
		//
		Sort sort = Sort.by("no").descending();
		
		Pageable pageable = PageRequest.of(2, 10,sort);
		
		Page<Board> result = repository.findAll(pageable);
		
		List<Board> list = result.getContent();
		
		System.out.println(list);
	}
	
	@Test
	public void 없는아이디로게시물등록하기() {
		//회원엔티티 생성
		Member member = Member.builder().id("user1").build();
		
		//회원테이블에 없는 아이디를 사용하면 에러남
		Board board = new Board(0,"1번글","내용입니다", member);
		
		repository.save(board);
	}
	
	@Test
	public void 게시물삭제() {
		repository.deleteAll();
	}
}
		