package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	

	@PostMapping("/auth/signUpProc")
	public ResponseDto<Integer> save(@RequestBody User user) { // username, password, email
		System.out.println("UserApiController : save 호출됨");

		userService.signUp(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바오브젝트를 JSON으로 변환해서 리턴 (Jackson)
	}

	@PutMapping("/user")
	public ResponseDto<Integer> updateUser(@RequestBody User user){
		userService.updateUser(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	
}