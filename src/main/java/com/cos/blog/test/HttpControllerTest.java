package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/*
 * RestController
 * Request by user -> Response(data)
 * Types that carry this annotation are treated as controllers 
 * where @RequestMapping methods assume @ResponseBody semantics by default.
 * 
 * Post, Put, Delete can't be requested by web browser
 */
@RestController
public class HttpControllerTest {
	
	private static final String Tag= "HttpControllerTest";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = new Member(1, "asdf", "123", "test@test.com");
		System.out.println(Tag+"getter: "+m.getId()); 
		m.setId(500);
		System.out.println(Tag+"setter: "+m.getId()); 
		
		Member m1 = new Member().builder().id(300).build();
		System.out.println(Tag+"builder: "+m1.getId());
		return "lombok Test 완료";
		
	}
	
	// http://localhost:8080/http/get (select)
	@GetMapping("/http/get")
	public String getTest(Member m) { // messageConverter(Spring boot)
		return "get 요청"+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}
//	public String getTest(@RequestParam int id, @RequestParam String username) {
//		return "get 요청"+id+", "+username;
//	}
		
	
	// http://localhost:8080/http/post (insert)
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) { // messageConverter(Spring boot)
		return "post 요청"+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}
	
	// http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청"+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}
	
	// http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
	
}
