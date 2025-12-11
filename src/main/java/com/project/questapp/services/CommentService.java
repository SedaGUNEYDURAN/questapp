package com.project.questapp.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.project.questapp.entities.Comment;
import com.project.questapp.repos.CommentRepository;

@Service
public class CommentService {

	private CommentRepository commentRepository;
	private PostService postService;
	private UserService userService;
	
	public CommentService(CommentRepository commentRepository, PostService postService, UserService userService) {
		this.commentRepository = commentRepository;
		this.postService = postService;
		this.userService = userService;
	}

	public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
		if(userId.isPresent() && postId.isPresent()) {
			return commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
		} else if(userId.isPresent()) {
			return commentRepository.findByUserId(userId.get());
			
		} else if(postId.isPresent()){
			return commentRepository.findByUserId(postId.get());
		} else 
			return commentRepository.findAll();
		
		
	}

}
