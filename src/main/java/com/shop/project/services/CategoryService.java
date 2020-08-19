package com.shop.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.shop.project.daos.CategoryDAO;
import com.shop.project.domain.Category;
import com.shop.project.dto.CategoryDTO;
import com.shop.project.services.exceptions.DataIntegrityException;
import com.shop.project.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryDAO dao;
	
	public Category find(Integer id) {
		Optional<Category> cat = dao.findById(id);
		return cat.orElseThrow(() -> new ObjectNotFoundException(
				"Item not found! Id: " + id + ", Type: " + Category.class.getName()));
		
	}
	
	public Category insert(Category cat) {
		cat.setId(null); // just in case
		return dao.save(cat);
	}
	
	public Category update(Category cat) {
		find(cat.getId());
		return dao.save(cat);
	}
	
	public void delete(Integer id) {
		
		find(id);
		try {
			dao.deleteById(id);	
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Not possible to delete a category with products.");
		}
		
	}
	
	public List<Category> findAll(){
		return dao.findAll();
	}
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageReq = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return dao.findAll(pageReq);
	}
	
	public Category fromDTO(CategoryDTO catDTO) {
		return new Category(catDTO.getId(), catDTO.getName());
	}
}
