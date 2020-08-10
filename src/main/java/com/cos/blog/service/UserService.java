package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 bean에 등록. IoC
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
	@Autowired
	private BCryptPasswordEncoder encoder;
	
    @Transactional
    public void signUp(User user) {
    	String rawPassword = user.getPassword();
    	String encPassword = encoder.encode(rawPassword);
    	
    	user.setRole(RoleType.USER);
    	user.setPassword(encPassword);
    	userRepository.save(user);
    }
    
    @Transactional
	public void updateUser(User user) {
    	// if update query, persistence of user object and update.
    	// and auto commit.
		User persistence = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("Fail of finding User");
		});
		
		// validation check
		if(persistence.getOauth() == null || persistence.getOauth().equals("")) {
			if(!user.getPassword().equals("")) {
				String rawPassword = user.getPassword();
				String encPassword = encoder.encode(rawPassword);
				persistence.setPassword(encPassword);
			}
			
			if(!user.getEmail().equals("")) {			
				persistence.setEmail(user.getEmail());
			}	

		}
		
			
	}
    
    @Transactional(readOnly = true)
	public User findUser(String username) {
    	User user = userRepository.findByUsername(username).orElseGet(()->{
    		return new User();
    	});
    	return user;		
	}
    
//    @Transactional(readOnly = true)
//	public User login(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
    
}