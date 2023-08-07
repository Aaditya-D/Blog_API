package com.bootproject.blog_api.services;

import com.bootproject.blog_api.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer postId);
	void deleteComment(Integer commentId);
	
}
