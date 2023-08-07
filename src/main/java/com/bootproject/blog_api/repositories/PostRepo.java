package com.bootproject.blog_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootproject.blog_api.entities.Category;
import com.bootproject.blog_api.entities.Post;
import com.bootproject.blog_api.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	List<Post> findByTitleContaining(String title);
}
