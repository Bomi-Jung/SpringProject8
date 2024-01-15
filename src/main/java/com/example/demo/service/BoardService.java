package com.example.demo.service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;

public interface BoardService {

	//게시물 등록
	int register(BoardDTO dto);
	
	//dto를 엔티티로 변환하는 메소드 
	default Board dtoToEntity(BoardDTO dto) {
		
		Board entity = Board.builder().no(dto.getNo()).title(dto.getTitle()).content(dto.getContent()).writer(dto.getWriter()).build();
		
		return entity;
	}
}
