package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

		//게시물을 등록하고 새로운 게시물 번호 반환 
		int no = service.register(dto);
		//목록호면에 새로운 게시물 번호 전달 
		redirectAttributes.addFlashAttribute("msg", no);
		//목록화면으로 이동. HTML경로 아님. URL 주소를 작성할 것
		return "redirect:/board/list";
	}
}
