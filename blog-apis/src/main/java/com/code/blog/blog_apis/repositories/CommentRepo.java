package com.code.blog.blog_apis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.blog.blog_apis.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>
{

}
