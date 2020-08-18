package com.shop.project.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.project.daos.CategoryDAO;
import com.shop.project.domain.Category;
import com.shop.project.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryDAO dao;
	
	public Category search(Integer id) {
		Optional<Category> cat = dao.findById(id);
		return cat.orElseThrow(() -> new ObjectNotFoundException(
				"Item not found! Id: " + id + ", Type: " + Category.class.getName()));
		
	}
	
	public Category insert(Category cat) {
		cat.setId(null); // just in case
		return dao.save(cat);
	}
}
