package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//@AuthenticationPrincipal PrincipaDetail principal
    @GetMapping({"","/"})
    public String index(Model model, @PageableDefault(size=3, sort="id", direction=Sort.Direction.DESC) Pageable pageable){ // how to find session at controller?
    	model.addAttribute("boards", boardService.boardList(pageable));
    	return "index";
    }
    
    // User auth needs
    @GetMapping("/board/writeForm")
    public String saveForm(){ // how to find session at controller?
    	return "board/writeForm";
    }
    
}