package com.code.blog.blog_apis.services;

import com.code.blog.blog_apis.entities.CommentDto;

public interface CommentService 
{
	CommentDto createComment(CommentDto commentDto, Integer postId);
	void deleteComment(Integer commentId);
}
