package com.code.blog.blog_apis.services;

import java.util.List;
import java.util.Locale.Category;

import com.code.blog.blog_apis.payloads.CategoryDto;

public interface CategoryService 
{
//	create category, update cat, get cat by id,get all cate, del 
	// by def in interface everything will get public
	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	CategoryDto getCategoryById(Integer categoryId);
	List<CategoryDto> getAllCategories();
	void delete(Integer categoryId);
}
