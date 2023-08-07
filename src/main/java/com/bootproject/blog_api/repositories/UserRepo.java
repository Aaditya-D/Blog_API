package com.bootproject.blog_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootproject.blog_api.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
