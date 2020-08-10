package com.cos.blog.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class UserController {
	
	public static String content_type = null;
	public static  String grant_type = null;
	public static String client_id = null;
	public static String redirect_uri = null;
	public static String cosKey = null;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	public UserService userService;
	
    @GetMapping("/auth/signupForm")
    public String signupForm(){
        
        return "user/signupForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm(){
        
        return "user/loginForm";
    }
    
    @GetMapping("/user/updateForm")
    public String updateForm(@AuthenticationPrincipal PrincipalDetail principal){        
        return "user/updateForm";
    }
    
    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code) { // data return controller
    	
    	// api info load from json
    	readJson();

    	// http header    	
    	RestTemplate rt = new RestTemplate();
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("Content-type", content_type);
    	
    	// http body
    	MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
    	params.add("grant_type", grant_type);
    	params.add("client_id", client_id);
    	params.add("redirect_uri",redirect_uri);
    	params.add("code",code);
    	
    	// http object definition with 
    	HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
    	ResponseEntity<String> response = rt.exchange(
    			"https://kauth.kakao.com/oauth/token", 
    			HttpMethod.POST, 
    			kakaoTokenRequest, 
    			String.class
    	);    	
    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	OAuthToken oauthToken = null;
    	try {
    		oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
    	}catch(JsonMappingException e) {
    		e.printStackTrace();
    	}catch(JsonProcessingException e) {
    		e.printStackTrace();
    	}
    	
    	RestTemplate rt2 = new RestTemplate();
    	HttpHeaders headers2 = new HttpHeaders();
    	headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
    	headers2.add("Content-type", content_type);
    	  	
    	// http object definition with 
    	HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers2);
    	ResponseEntity<String> response2 = rt2.exchange(
    			"https://kapi.kakao.com/v2/user/me", 
    			HttpMethod.POST, 
    			kakaoProfileRequest, 
    			String.class
    	); 
    	
    	
    	ObjectMapper objectMapper2 = new ObjectMapper();
    	KakaoProfile kakaoProfile = null;
    	try {
    		kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
    	}catch(JsonMappingException e) {
    		e.printStackTrace();
    	}catch(JsonProcessingException e) {
    		e.printStackTrace();
    	}
    	
    	// User object
    	User kakaoUser = User.builder()
    			.username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
    			.password(cosKey)
    			.email(kakaoProfile.getKakao_account().getEmail())
    			.role(RoleType.USER)
    			.oauth("kakao")
    			.build();
    	
    	// Check existing users
    	User originUser = userService.findUser(kakaoUser.getUsername());
    	if(originUser.getUsername() == null) {
    		userService.signUp(kakaoUser);
    	}
    	
    	// Login
    	Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		    	
    	return "redirect:/";
    }
    
    public void readJson() {
    	JSONParser parser = new JSONParser();

        try {     
            Object obj = parser.parse(new FileReader("C:\\Users\\lhs-DT\\Desktop\\coding\\spring\\kakaoAuth.json"));
            
            JSONObject jsonObject =  (JSONObject) obj;
            
            content_type = (String) jsonObject.get("content_type");
			grant_type =(String) jsonObject.get("grant_type");
			client_id = (String) jsonObject.get("client_id");
			redirect_uri = (String) jsonObject.get("redirect_uri");
			cosKey = (String) jsonObject.get("cosKey");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();     
        } catch (ParseException e) {
			e.printStackTrace();
		}

    }
    
    
}