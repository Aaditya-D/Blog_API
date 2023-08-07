package com.bootproject.blog_api.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bootproject.blog_api.entities.Category;
import com.bootproject.blog_api.entities.Post;
import com.bootproject.blog_api.entities.User;
import com.bootproject.blog_api.exceptions.ResourceNotFoundException;
import com.bootproject.blog_api.payloads.PostDto;
import com.bootproject.blog_api.payloads.PostResponse;
import com.bootproject.blog_api.repositories.CategoryRepo;
import com.bootproject.blog_api.repositories.PostRepo;
import com.bootproject.blog_api.repositories.UserRepo;
import com.bootproject.blog_api.services.PostService;


@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User ", "User Id", userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category ", "CategoryId ", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post createdPost = this.postRepo.save(post);
		
		return this.modelMapper.map(createdPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost = this.postRepo.save(post);
		
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {

		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		  
		Sort sort = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		
		Page<Post> postPage = this.postRepo.findAll(pageable);
		
		List<Post> posts = postPage.getContent();
		
		List<PostDto> allPosts = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse response = new PostResponse();
		response.setContent(allPosts);
		response.setPageNumber(postPage.getNumber());
		response.setPageSize(postPage.getSize());
		response.setTotalElements(postPage.getTotalElements());
		response.setTotalPages(postPage.getTotalPages());
		response.setLastpage(postPage.isLast());
		
		return response;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post ", "postId", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer catId) {

		 Category category = this.categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("category ", "categoryId ", catId));
		 List<Post> posts = this.postRepo.findByCategory(category);
		 List<PostDto> postDtos  = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userid) {

		User user = this.userRepo.findById(userid).orElseThrow(()-> new ResourceNotFoundException("user " , "userId ", userid));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keywords) {

		List<Post> posts = this.postRepo.findByTitleContaining(keywords);
		List<PostDto> postDto = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}
	
	
}
