package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;

@Service // 서비스 클래스로 지정
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository repository;

	@Override
	public int register(BoardDTO dto) {

		Board entity = dtoToEntity(dto);

		repository.save(entity);

		int newNo = entity.getNo();

		System.out.println(dto);

		return newNo;
	}


	@Override
	public BoardDTO read(int no) {
		Optional<Board> result = repository.findById(no);

		if (result.isPresent()) {
			Board board = result.get();
			BoardDTO boardDTO = entityToDto(board);
			return boardDTO;
		}
		return null;

	}

	@Override
	public void modify(BoardDTO dto) {

		Optional<Board> result = repository.findById(dto.getNo());

		if (result.isPresent()) {

			Board entity = result.get();
			//기존 엔티티에서 제목과 내용만 변경
			entity.setTitle(dto.getTitle());
			entity.setContent(dto.getContent());
			//다시 저장
			repository.save(entity);
		}
	}

	@Override
	public int remove(int no) {

		Optional<Board> result = repository.findById(no);
		
		if (result.isPresent()) {
			repository.deleteById(no);
			return 1; //성공
		}else {
			return 0; //실패
		}
		}


	//목록조회 메소드 
	@Override
	public Page<BoardDTO> getList(int pageNumber) {

		//페이지 번호를 인덱스로 변경
		int pageNum = (pageNumber == 0) ? 0 : pageNumber -1;
		
		//페이지번호, 개수, 정렬방식을 입력하여 페이지 정보 생성
		PageRequest pageable = PageRequest.of(pageNum, 10, Sort.by("no").descending());
		
		//게시물 목록 조회
		Page<Board> entityPage = repository.findAll(pageable);
		
		//스트림을 사용하여 엔티티 리스트를 DTO 리스트로 변환
		Page<BoardDTO> dtoPage = entityPage.map(entity -> entityToDto(entity));
	
		return dtoPage;
	}
	
}
