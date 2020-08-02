package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String index(Model model){ // how to find session at controller?
    	model.addAttribute("boards", boardService.boardList());
    	return "index";
    }
    
    // User auth needs
    @GetMapping("/board/writeForm")
    public String saveForm(){ // how to find session at controller?
    	return "board/writeForm";
    }
    
}