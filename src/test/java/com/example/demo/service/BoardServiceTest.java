package com.example.demo.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.example.demo.dto.BoardDTO;

@SpringBootTest
public class BoardServiceTest {

	
	@Autowired
	BoardService service;
	
	@Test
	public void  게시물목록조회() {
		//첫번째 페이지 조회
		Page<BoardDTO> page = service.getList(1);
		//게시물 목록 꺼내기 
		List<BoardDTO> list = page.getContent();
		for(BoardDTO dto : list) {
			System.out.println(dto);
		}
	}
}
