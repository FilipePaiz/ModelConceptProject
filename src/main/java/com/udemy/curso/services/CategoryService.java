package com.udemy.curso.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.curso.daos.CategoryDAO;
import com.udemy.curso.domain.Category;
import com.udemy.curso.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryDAO dao;
	
	public Category search(Integer id) {
		Optional<Category> cat = dao.findById(id);
		return cat.orElseThrow(() -> new ObjectNotFoundException(
				"Item not found! Id: " + id + ", Type: " + Category.class.getName()));
		
	}
}
