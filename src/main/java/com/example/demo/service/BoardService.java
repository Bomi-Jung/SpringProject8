package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;

public interface BoardService {

	//게시물 등록
	int register(BoardDTO dto);
	
	//게시물 목록 조회
	List<BoardDTO> getList();
	
	//dto를 엔티티로 변환하는 메소드 
	default Board dtoToEntity(BoardDTO dto) {
		
		Board entity = Board.builder().no(dto.getNo()).title(dto.getTitle()).content(dto.getContent()).writer(dto.getWriter()).build();
		
		return entity;
	}
	//엔티티를 dto로 변환하는 메소드
	default BoardDTO entityToDto(Board entity) {
		
		BoardDTO dto = BoardDTO.builder().no(entity.getNo()).title(entity.getTitle()).content(entity.getContent()).writer(entity.getWriter()).regDate(entity.getRegDate()).modDate(entity.getModDate()).build();
		
		return dto;
	}
}
