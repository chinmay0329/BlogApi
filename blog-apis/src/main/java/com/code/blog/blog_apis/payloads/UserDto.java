package com.code.blog.blog_apis.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto 
{

	private int id;
	
//	@Column(name="user_name", nullable = false, length=100)
	@NotEmpty
	@Size(min=4, message="username must be => 4")
	private String name;
	
	@Email(message="email address is not valid!!!")
	private String email;
	
	@NotEmpty
	@Size(min=3, message="miv length is 3")
	private String password;
	
	@NotEmpty
	private String about;
	
}
