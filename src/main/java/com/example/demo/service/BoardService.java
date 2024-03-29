package com.example.demo.service;

import org.springframework.data.domain.Page;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.entity.Member;

public interface BoardService {

	//게시물 등록
	int register(BoardDTO dto);
	
	//게시물 목록 조회
	Page<BoardDTO> getList(int pageNumber);
	
	//게시물 상세조회
	BoardDTO read(int no);
	
	//dto를 엔티티로 변환하는 메소드 
	default Board dtoToEntity(BoardDTO dto) {
		
		//작성자 객체 생성
		Member member = Member
				.builder()
				.id(dto.getWriter())
				.build();
		
		Board entity = Board.builder()
				.no(dto.getNo())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(member)
				.build();
		
		return entity;
	}
	//엔티티를 dto로 변환하는 메소드
	default BoardDTO entityToDto(Board entity) {
	
		
		BoardDTO dto = BoardDTO
				.builder()
				.no(entity.getNo())
				.title(entity.getTitle())
				.content(entity.getContent())
				.writer(entity.getWriter().getId())
				//체인메소드(작성자를 꺼내서 아이디를 꺼냄)
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.build();
		
		return dto;
	}
	
	//게시물 수정
	void modify(BoardDTO dto);
	
	//게시물 삭제
	int remove(int no);
	
	
	
}
