package com.shop.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.project.controllers.utils.URL;
import com.shop.project.domain.Product;
import com.shop.project.dto.ProductDTO;
import com.shop.project.services.ProductService;

@RestController
@RequestMapping(value="/products")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Product> find(@PathVariable Integer id) {
		
		Product prod = service.find(id);
		
		return ResponseEntity.ok().body(prod);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProductDTO>> findPage(
			@RequestParam(value="name", defaultValue="") String name,
			@RequestParam(value="categories", defaultValue="") String categories,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		String nameDecode = URL.decodeParam(name);
		List<Integer> ids = URL.decodeIntList(categories);
		
		Page<Product> pageProd = service.search(nameDecode, ids, page, linesPerPage, orderBy, direction);
		
		Page<ProductDTO> dtoList = pageProd.map(prod -> new ProductDTO(prod));
		
		return ResponseEntity.ok().body(dtoList);
	}
}
