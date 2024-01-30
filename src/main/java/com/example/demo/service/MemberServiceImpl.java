package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository repository;

	@Override
	public Page<MemberDTO> getList(int pageNumber) { // 페이지 번호 받기

		/// 페이지 index는 0부터 시작
		int pageIndex = (pageNumber == 0) ? 0 : pageNumber - 1;

		// 등록일을 기준으로 역정렬
		Sort sort = Sort.by("regDate").descending();

		// 페이지 번호, 데이터 건수, 정렬방법을 입력하여 페이징 조건 생성
		Pageable pageable = PageRequest.of(pageIndex, 10, sort);

		Page<Member> entityPage = repository.findAll(pageable);

		// 엔티티리스트를 DTO 리스트로 변환
		Page<MemberDTO> dtoPage = entityPage.map(entity -> entityToDTO(entity));

		return dtoPage;
	}

	@Override
	public boolean register(MemberDTO dto) {
		
		//아이디 중복 여부 확인
		String id = dto.getId();
		MemberDTO getDto = read(id);

		//해당아이디가 사용되고 있다면, false 반환
		if (getDto != null) {
			System.out.println("사용중인 아이디입니다.");

			return false;
		}
		//해당아이디가 사용되지 않는다면, 회원 등록 후  true 반환
		Member entity = dtoToEntity(dto);
		repository.save(entity);
		return true;
	}

	@Override
	public MemberDTO read(String id) {

		Optional<Member> result = repository.findById(id);

		if (result.isPresent()) {
			Member member = result.get();
			return entityToDTO(member);
		} else {
			return null;
		}
	}

}
