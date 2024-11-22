package com.code.blog.blog_apis.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto 
{
	private Integer categoryId;
	
	@NotEmpty @Size(min=3, message = "Min length is 3")
	private String categoryTitle;
	
	@NotEmpty @Size(min=5, message = "Min length is 5")
	private String categoryDescription;
}
