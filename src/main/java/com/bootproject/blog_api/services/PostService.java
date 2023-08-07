package com.bootproject.blog_api.services;

import java.util.List;

import com.bootproject.blog_api.entities.Post;
import com.bootproject.blog_api.payloads.PostDto;
import com.bootproject.blog_api.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);

	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy ,String sortDir);
	
	PostDto getPostById(Integer postId);
	
	List<PostDto> getPostsByCategory(Integer catId);
	 
	List<PostDto> getPostsByUser(Integer userid);
	
	List<PostDto> searchPosts(String Keyword);
	
}
