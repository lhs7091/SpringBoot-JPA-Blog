package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;
    
    @Autowired
    private ReplyRepository replyRepository;
    
    @Autowired
    private UserRepository userRepository;
	
    @Transactional
    public void write(Board board, User user) {
    	board.setCount(0);
    	board.setUser(user);
    	boardRepository.save(board);
    }
    
    @Transactional(readOnly = true)
	public Page<Board> boardList(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Board viewContents(int id) {
		return boardRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("Contents read fail : ID can't find");
		});		
	}
	
	@Transactional
	public void delete(int id) {
		boardRepository.deleteById(id);		
	}
	
	@Transactional
	public void updateContents(int id, Board requestBoard) {
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("Contents read fail : ID can't find");
				});// Durability complete
		board.setTitle(requestBoard.getContent());
		board.setContent(requestBoard.getContent());
		
		// when service finish, transaction finish too.
		// dirty checking -> auto update in db(commit)
		
	}
	
	@Transactional
	public void writeReply(ReplySaveRequestDto replySaveRequestDto) {		
			
		replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
		
	}
	
	@Transactional
	public void removeReply(int replyId) {
		// TODO Auto-generated method stub
		replyRepository.deleteById(replyId);
	}
    
    
}