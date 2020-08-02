package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;
	
    @Transactional
    public void write(Board board, User user) {
    	board.setCount(0);
    	board.setUser(user);
    	boardRepository.save(board);
    }
    
	public Page<Board> boardList(Pageable pageable) {
		// TODO Auto-generated method stub
		return boardRepository.findAll(pageable);
	}
    
    
}