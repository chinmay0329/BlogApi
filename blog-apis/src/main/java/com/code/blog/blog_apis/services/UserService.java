package com.code.blog.blog_apis.services;

import java.util.List;

import com.code.blog.blog_apis.payloads.UserDto;

public interface UserService 
{
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);
}
