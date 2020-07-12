package com.cos.blog.repository;

import com.cos.blog.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

//DAO
//resist Bean automatically
//@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    
}