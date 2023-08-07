package com.bootproject.blog_api.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootproject.blog_api.entities.Comment;
import com.bootproject.blog_api.entities.Post;
import com.bootproject.blog_api.exceptions.ResourceNotFoundException;
import com.bootproject.blog_api.payloads.CommentDto;
import com.bootproject.blog_api.repositories.CommentRepo;
import com.bootproject.blog_api.repositories.PostRepo;
import com.bootproject.blog_api.services.CommentService;

@Service
public class CommentServiceImpl  implements CommentService{

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {

		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "post Id", postId));
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		
		Comment savedComment = this.commentRepo.save(comment);
		
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {

		Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "comment Id", commentId));
		this.commentRepo.delete(comment);
	}

}
