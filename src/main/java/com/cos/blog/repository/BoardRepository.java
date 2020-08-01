package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Board;

//DAO
//resist Bean automatically
//@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{

}


