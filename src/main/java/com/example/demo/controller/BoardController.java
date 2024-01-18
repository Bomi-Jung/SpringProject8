package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.BoardDTO;
import com.example.demo.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService service;

	// 메인화면
	@GetMapping("/main")
	public void main() {

	}

	// 목록화면
	@GetMapping("/list")
	public void list(Model model) {
		List<BoardDTO> list = service.getList();

		model.addAttribute("list", list);
	}

	// 등록화면
	@GetMapping("/register")
	public void register() {

	}

	// 등록처리
	@PostMapping("/register")
	public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes) {

		// 게시물을 등록하고 새로운 게시물 번호 반환
		int no = service.register(dto);
		// 목록화면에 새로운 게시물 번호 전달
		redirectAttributes.addFlashAttribute("msg", no);
		// 목록화면으로 이동. HTML경로 아님. URL 주소를 작성할 것
		return "redirect:/board/list";
	}

	// 상세화면
	@GetMapping("/read")
	public void read(@RequestParam(name = "no") int no, Model model) {
		BoardDTO dto = service.read(no);
		model.addAttribute("dto", dto);
	}

	// 수정화면
	@GetMapping("/modify")
	public void modify(@RequestParam(name = "no") int no, Model model) {

		BoardDTO dto = service.read(no);

		model.addAttribute("dto", dto);
	}

	@PostMapping("/modify")
	public String modifyPost(BoardDTO dto, RedirectAttributes redirectAttributes) {
		// 게시물 수정
		service.modify(dto);
		// 리다이렉트 주소에 파라미터 추가(?no=1)
		redirectAttributes.addAttribute("no", dto.getNo());
		// 상세화면으로 이동
		return "redirect:/board/read";

	}

	// 삭제처리
	@PostMapping("/remove")
	public String removePost(@RequestParam(name = "no")int no) {
		service.remove(no);
		return "redirect:/board/list";
	}
}
