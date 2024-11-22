package com.code.blog.blog_apis.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.blog.blog_apis.entities.User;
import com.code.blog.blog_apis.exceptions.ResourceNotFoundException;
import com.code.blog.blog_apis.payloads.UserDto;
import com.code.blog.blog_apis.repositories.UserRepo;
import com.code.blog.blog_apis.services.UserService;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public UserDto createUser(UserDto userDto) 
	{
		User user=dtoToUser(userDto);
		User savedUser=userRepo.save(user);
		return userToUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) 
	{
		
		User user=userRepo.findById(userId).orElseThrow(()-> new 
				ResourceNotFoundException("User", "id", userId));
		
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		
		User updatedUser=userRepo.save(user);
		
		return userToUserDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) 
	{
		User user=userRepo.findById(userId).orElseThrow(()-> new 
				ResourceNotFoundException("User", "id", userId));
		return userToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() 
	{
		List<User> userList=userRepo.findAll();
		
		//Method1
//		List<UserDto> dtoUsers = null;
//		for (User users : userList) 
//		{
//			dtoUsers.add(userToUserDto(users));
//		}
//		return dtoUsers;
		
		//Method2 by stream api
		List<UserDto> dtoList=userList.stream().map(user->userToUserDto(user)).collect(Collectors.toList());
		
		return dtoList;
	}

	@Override
	public void deleteUser(Integer userId) 
	{
		User user=userRepo.findById(userId).orElseThrow(()-> new 
				ResourceNotFoundException("User", "id", userId));
		userRepo.delete(user);
	}
	
	private User dtoToUser(UserDto userDto)
	{
		User user=modelmapper.map(userDto, User.class);
		
//		User user=new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setPassword(userDto.getPassword());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());;
		return user;
	}
	
	private UserDto userToUserDto(User user)
	{
		UserDto userDto=new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setPassword(user.getPassword());
		userDto.setEmail(user.getEmail());
		userDto.setAbout(user.getAbout());
		return userDto;
	}

}
