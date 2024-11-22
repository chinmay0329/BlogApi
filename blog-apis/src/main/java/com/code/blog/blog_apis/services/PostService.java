package com.code.blog.blog_apis.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.code.blog.blog_apis.entities.Post;
import com.code.blog.blog_apis.payloads.PostDto;

public interface PostService 
{
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	public PostDto updatePost(PostDto postDto, Integer postId);
	public List<PostDto> getAllPosts();
	public PostDto getPostById(Integer postId);
	public void deletePost(Integer postId);
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	List<PostDto> getPostsByUser(Integer userId);
	
	List<PostDto> searchPosts(String keyword);
}
