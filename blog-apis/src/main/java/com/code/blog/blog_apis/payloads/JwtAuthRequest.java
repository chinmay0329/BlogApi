package com.code.blog.blog_apis.payloads;

import lombok.Data;

@Data
public class JwtAuthRequest 
{
	private String username;
	private String password;
}
