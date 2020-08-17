package com.shop.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shop.project.domain.Category;
import com.shop.project.services.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoryController {

	@Autowired
	private CategoryService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Category cat = service.search(id);
		
		return ResponseEntity.ok().body(cat);
	}
}