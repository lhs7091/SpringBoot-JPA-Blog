package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

//DAO
//resist Bean automatically
//@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    
	// select * from user where username=1?;
	Optional<User> findByUsername(String username);
}


// JPA naming Query Strategy
// select * from user where username=? and password=?;
// User findByUsernameAndPassword(String username, String password);

//@Query(value="select * from user where username=?1 and password=?2", nativeQuery=True)
//User login(String username, String password);