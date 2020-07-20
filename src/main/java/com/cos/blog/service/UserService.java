package com.cos.blog.service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 스프링이 컴포넌트 스캔을 통해서 bean에 등록. IoC
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void signUp(User user){
        userRepository.save(user);
    }
    
}