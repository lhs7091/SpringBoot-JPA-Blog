package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	//@AuthenticationPrincipal PrincipaDetail principal
    @GetMapping({"","/"})
    public String index(){ // how to find session at controller?
    	return "index";
    }
    
    // User auth needs
    @GetMapping("/board/writeForm")
    public String saveForm(){ // how to find session at controller?
    	return "board/writeForm";
    }
    
}