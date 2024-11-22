package com.code.blog.blog_apis.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.code.blog.blog_apis.entities.User;
import com.code.blog.blog_apis.exceptions.ResourceNotFoundException;
import com.code.blog.blog_apis.repositories.UserRepo;

@Service
public class CustomerUserDetailService implements UserDetailsService
{
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		// here username is referred as email
//		loading user from database by useremail/username
		User user=userRepo.findByEmail(username).orElseThrow
		(()->new ResourceNotFoundException("user", "email : "+username, 0) );
		
//		now from here we are just getting User object , but we need to
//		return UserDetails object, so we will make User class implement
//		UserDetails
	
		return user; 
		// able to retail user since User class implemented UserDetails
	}

}
