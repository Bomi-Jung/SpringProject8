package com.example.demo.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.entity.Board;

@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	BoardRepository repository;
	
	
	@Test
	public void 게시물30개추가() {
		
		for(int i =1; i<= 30; i++) {
		Board board = Board.builder().title(i + "번글")
				.content("안녕하세요").writer("둘리").build();
		
		repository.save(board);
	
		}
	}
	
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
	
	
}
		