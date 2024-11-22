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

import com.code.blog.blog_apis.payloads.CategoryDto;
import com.code.blog.blog_apis.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/categories")
public class CategoryController 
{
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getACategory(@PathVariable Integer categoryId)
	{
		CategoryDto catDto= categoryService.getCategoryById(categoryId);
		return ResponseEntity.ok(catDto);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories()
	{
		List<CategoryDto> dtoList = categoryService.getAllCategories();
		return ResponseEntity.ok(dtoList);
	}
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto createdCategory = categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
											@PathVariable Integer categoryId)
	{
		CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.CREATED);
	} 
	
	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable Integer categoryId)
	{
		categoryService.delete(categoryId);
	} 
}
