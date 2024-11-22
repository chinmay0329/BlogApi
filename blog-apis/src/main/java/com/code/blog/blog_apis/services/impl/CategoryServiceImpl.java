package com.code.blog.blog_apis.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.blog.blog_apis.entities.Category;
import com.code.blog.blog_apis.exceptions.ResourceNotFoundException;
import com.code.blog.blog_apis.payloads.CategoryDto;
import com.code.blog.blog_apis.repositories.CategoryRepo;
import com.code.blog.blog_apis.repositories.UserRepo;
import com.code.blog.blog_apis.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService
{
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) 
	{
		Category category= dtoToCategory(categoryDto);
		Category savedUser=categoryRepo.save(category);
		return categoryToDto(savedUser);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) 
	{
		Category category=categoryRepo.findById(categoryId).orElseThrow(()->
											new ResourceNotFoundException("Category", "categoryId", categoryId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category savedCategory=categoryRepo.save(category);
		return categoryToDto(savedCategory);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) 
	{
		Category category=categoryRepo.findById(categoryId).orElseThrow(()->
								new ResourceNotFoundException
								("Category", "categoryId", categoryId));
		
		return categoryToDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategories() 
	{
		List<Category> categoryList=categoryRepo.findAll();
		List<CategoryDto> dtoList= categoryList.stream().map(category-> categoryToDto(category))
							.collect(Collectors.toList());
		return dtoList;
	}

	@Override
	public void delete(Integer categoryId) 
	{
		Category category=categoryRepo.findById(categoryId).orElseThrow(()->
						new ResourceNotFoundException
						("Category", "categoryId", categoryId));
		categoryRepo.delete(category);
		
	}
	
	private CategoryDto categoryToDto(Category category)
	{
		CategoryDto dto=modelMapper.map(category, CategoryDto.class);
		return dto;
	}
	
	private Category dtoToCategory(CategoryDto dto)
	{
		Category category=modelMapper.map(dto, Category.class);
		return category;
	}

}
