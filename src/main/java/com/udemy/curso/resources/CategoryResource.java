package com.udemy.curso.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.curso.domain.Category;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {

	@RequestMapping(method=RequestMethod.GET)
	public List<Category> listing() {
		
		Category cat1 = new Category(1, "Computador");
		Category cat2 = new Category(2, " Periféricos");
		
		List<Category> categoryList = new ArrayList<>();
		categoryList.add(cat1);
		categoryList.add(cat2);
		
		return categoryList;
	}
}
