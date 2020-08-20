package com.shop.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.shop.project.daos.CategoryDAO;
import com.shop.project.daos.ProductDAO;
import com.shop.project.domain.Category;
import com.shop.project.domain.Product;
import com.shop.project.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductDAO dao;
	
	@Autowired
	private CategoryDAO catDao;
	
	public Product find(Integer id) {
		Optional<Product> prod = dao.findById(id);
		return prod.orElseThrow(() -> new ObjectNotFoundException(
				"Item not found! Id: " + id + ", Type: " + Product.class.getName()));
		
	}
	
	public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageReq = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		List<Category> categories = catDao.findAllById(ids);
		
		return dao.search(name, categories, pageReq);
	}
}
