package com.bootproject.blog_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootproject.blog_api.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
