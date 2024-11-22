package com.code.blog.blog_apis.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.blog.blog_apis.entities.Category;
import com.code.blog.blog_apis.entities.Post;
import com.code.blog.blog_apis.entities.User;
import com.code.blog.blog_apis.exceptions.ResourceNotFoundException;
import com.code.blog.blog_apis.payloads.PostDto;
import com.code.blog.blog_apis.repositories.CategoryRepo;
import com.code.blog.blog_apis.repositories.PostRepo;
import com.code.blog.blog_apis.repositories.UserRepo;
import com.code.blog.blog_apis.services.PostService;

@Service
public class PostServiceImpl implements PostService
{
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) 
	{
		User user= userRepo.findById(userId).orElseThrow
				(
					()-> new ResourceNotFoundException
					("User", "user id", userId)
				);
		
		Category category= categoryRepo.findById(categoryId).orElseThrow
						(
							()-> new ResourceNotFoundException
							("Category", "category id", categoryId)
						);
		
		Post post= dtoToPost(postDto);
		post.setImageName("default.png");
		post.setAddDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post savedPost=postRepo.save(post);
		return postToDto(savedPost);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) 
	{
		Post post= postRepo.findById(postId).orElseThrow(()->
						new ResourceNotFoundException
						("Category", "categoryId", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post savedPost=postRepo.save(post);
		return postToDto(savedPost);
	}

	@Override
	public List<PostDto> getAllPosts() 
	{
		List<Post> postList= postRepo.findAll();
		List<PostDto> dtoList= postList.stream().map(post -> postToDto(post))
								.collect(Collectors.toList());
		return dtoList;
	}

	@Override
	public PostDto getPostById(Integer postId) 
	{
		Post post= postRepo.findById(postId).orElseThrow(()->
					new ResourceNotFoundException
					("Category", "categoryId", postId));
		
		return postToDto(post);
	}

	@Override
	public void deletePost(Integer postId) 
	{
		
		Post post= postRepo.findById(postId).orElseThrow(()->
					new ResourceNotFoundException
					("Category", "categoryId", postId));
		postRepo.delete(post);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) 
	{
		Category category= categoryRepo.findById(categoryId).orElseThrow
				(
					()-> new ResourceNotFoundException
					("Category", "category id", categoryId)
				);
//		List <Post> postList= category.getPosts();
		List <Post> postList= postRepo.findByCategory(category);
		
		List<PostDto> dtoList= postList.stream().map(post-> postToDto(post))
				.collect(Collectors.toList());
				             
		return dtoList;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) 
	{
		User user= userRepo.findById(userId).orElseThrow
				(
					()-> new ResourceNotFoundException
					("User", "user id", userId)
				);
//		List <Post> postList= user.getPosts();
		List <Post> postList= postRepo.findByUser(user);
		
		List<PostDto> dtoList= postList.stream().map(post-> postToDto(post))
									.collect(Collectors.toList());
		return dtoList;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) 
	{
		List<Post> postList=postRepo.findByTitleContaining(keyword);
		List<PostDto> dtoList= postList.stream().map(post-> postToDto(post))
								.collect(Collectors.toList());
		return dtoList;
	}
	
	private PostDto postToDto(Post post)
	{
		PostDto dto= modelMapper.map(post, PostDto.class);
		return dto;
	}
	
	private Post dtoToPost(PostDto dto)
	{
		Post post= modelMapper.map(dto, Post.class);
		return post;
	}
	
}
