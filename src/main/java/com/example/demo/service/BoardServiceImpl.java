package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<BoardDTO> getList() {

		// 데이터 베이스에서 게시물 목록을 가져오기
		List<Board> result = repository.findAll();
		// 리스트 생성
		List<BoardDTO> list = new ArrayList<>();

		list = result.stream()// 리스트에서 스트림 생성
				.map(entity -> entityToDto(entity))// 중산연산으로 엔티티를 dto로 변환
				.collect(Collectors.toList()); //

		return list;
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
	
}
