package com.example.demo.service;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.example.demo.dto.MemberDTO;

@SpringBootTest
public class MemberServieTest {

	@Autowired
	MemberService service;
	
	@Test
	public void 회원목록조회() {
		Page<MemberDTO> page = service.getList(2); //첫번째 페이지 조회
		List<MemberDTO> list = page.getContent(); // 회원 목록 꺼내기
		for(MemberDTO dto : list) {
			System.out.println(dto);
		}
	}
	
	@Test
	public void 회원등록() {
		 MemberDTO dto = MemberDTO.builder()
				 .id("user1")
				 .password("1234")
				 .name("둘리")
				 .build();
		 
		 boolean isSuccess = service.register(dto);
		 if(isSuccess) {
			 System.out.println("회원이 등록되었습니다.");
		 }else {
			 System.out.println("중복된 회원 입니다.");
		 }
	}
}
