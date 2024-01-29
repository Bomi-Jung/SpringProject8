package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Member;

@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	BoardRepository boardRepository;

	@Test
	public void 회원등록() {
		Member member = new Member("user1", "1234", "둘리");
		memberRepository.save(member);
	}
	
	@Test
	public void 회원일괄등록() {
		List<Member> list = new ArrayList<>();
		
		for(int i = 0; i<30; i ++) {
			list.add(new Member("user"+i, "1234", "둘리"));
		}
		
		memberRepository.saveAll(list);
	} 

	@Test
	public void 회원목록조회() {
		List<Member> list = memberRepository.findAll();
		for (Member member : list) {
			System.out.println(member);
		}
	}

	@Test
	public void 회원단건조회() {
		Optional<Member> result = memberRepository.findById("user1");
		if (result.isPresent()) {
			Member member = result.get();
			System.out.println(member);
		}
	}
	
	@Test
	public void 회원수정() {
		Optional<Member> result = memberRepository.findById("user1");
		Member member = result.get();
		member.setName("또치");
		memberRepository.save(member);
		
	}
	
	@Test
	public void 회원삭제() {
		memberRepository.deleteById("user1");
	
	//게시물이 없는 회원은 삭제해도 문제가 없지만
	//게시물이 있는 경우에는 삭제할 수 없음(참조관계가 생겼기 때문에)
		
	//데이터생성: 부모 -> 자식
	//데이터삭제: 자식 -> 부모 
	}
	
	@Test
	public void 모든회원삭제() {
		boardRepository.deleteAll();
		
		memberRepository.deleteAll();
	}
	
	@Test
	public void 게시물을작성한회원삭제() {
		Member member = Member.builder().id("user2").build();
		
		boardRepository.deleteWriter(member);
		memberRepository.deleteById("user2");
	}
	
}
