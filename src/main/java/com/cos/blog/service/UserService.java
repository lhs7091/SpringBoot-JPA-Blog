package com.cos.blog.service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 스프링이 컴포넌트 스캔을 통해서 bean에 등록. IoC
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public int signUp(User user){
        try{
            userRepository.save(user);
            return 1;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("UserService : signUp() : "+e.getMessage());
        }
        return -1;
    }
    
}