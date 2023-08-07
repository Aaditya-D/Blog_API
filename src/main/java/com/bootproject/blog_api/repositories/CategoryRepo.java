package com.bootproject.blog_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootproject.blog_api.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
