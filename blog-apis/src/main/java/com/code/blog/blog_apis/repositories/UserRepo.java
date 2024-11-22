package com.code.blog.blog_apis.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.blog.blog_apis.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> 
{
	Optional<User> findByEmail(String email);
}
