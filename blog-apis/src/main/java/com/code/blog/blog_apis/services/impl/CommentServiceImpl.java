package com.code.blog.blog_apis.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.blog.blog_apis.entities.Comment;
import com.code.blog.blog_apis.entities.CommentDto;
import com.code.blog.blog_apis.entities.Post;
import com.code.blog.blog_apis.exceptions.ResourceNotFoundException;
import com.code.blog.blog_apis.repositories.CommentRepo;
import com.code.blog.blog_apis.repositories.PostRepo;
import com.code.blog.blog_apis.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService
{
	@Autowired
	private PostRepo postRepo; 
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) 
	{
		Post post= postRepo.findById(postId).orElseThrow(()->
							new ResourceNotFoundException("Post", "post id", postId));
		Comment comment= dtoToComment(commentDto);
		
		comment.setPost(post);
		
		Comment savedComment= commentRepo.save(comment);
		return commentToDto(savedComment);
	}

	@Override
	public void deleteComment(Integer commentId) 
	{
		Comment comment= commentRepo.findById(commentId).orElseThrow(()->
						new ResourceNotFoundException("Comment", "Comment id", commentId));
		commentRepo.delete(comment);
	}
	
	private CommentDto commentToDto(Comment comment)
	{
		CommentDto dto= modelMapper.map(comment, CommentDto.class);
		return dto;
	}
	
	private Comment dtoToComment(CommentDto dto)
	{
		Comment comment= modelMapper.map(dto, Comment.class);
		return comment;
	}

}
