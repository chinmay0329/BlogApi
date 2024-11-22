package com.code.blog.blog_apis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.blog.blog_apis.payloads.PostDto;
import com.code.blog.blog_apis.services.PostService;
import com.code.blog.blog_apis.services.UserService;

@RestController
@RequestMapping("api/")
public class PostController 
{
	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost
	(@RequestBody PostDto postDto,@PathVariable Integer userId,
			@PathVariable Integer categoryId)
	{
		PostDto createdPost= postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
	}
	
	//get posts by User
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostsByUser(
			@PathVariable Integer userId)
	{
		List<PostDto> dtoList= postService.getPostsByUser(userId);
//		return ResponseEntity.ok(dtoList);
		return new ResponseEntity<List<PostDto>>(dtoList,HttpStatus.OK);
	}
	
	
	//get posts by Category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostsByCategory(
			@PathVariable Integer categoryId)
	{
		List<PostDto> dtoList= postService.getPostsByCategory(categoryId);
//		return ResponseEntity.ok(dtoList);
		return new ResponseEntity<List<PostDto>>(dtoList,HttpStatus.OK);
	}
	
	//get post by id
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
	{
		PostDto dto= postService.getPostById(postId);
		return new ResponseEntity<PostDto>(dto, HttpStatus.OK);
	}
	
	//get all posts
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPosts()
	{
		List<PostDto> dtoList= postService.getAllPosts();
		return new ResponseEntity<List<PostDto>>(dtoList, HttpStatus.OK);
	}
	
	//delete post
	@DeleteMapping("/post/{postId}")
	public void deletePost(@PathVariable Integer postId)
	{
		postService.deletePost(postId);
	}
	
	//update post
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
				@PathVariable Integer postId)
	{
		PostDto dto= postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(dto, HttpStatus.OK);
	}
	
	//search
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchByTitle
		(@PathVariable String keywords)
	{
		List<PostDto> dtoList= postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(dtoList, HttpStatus.OK);
	}
	
}
