package com.shop.project.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shop.project.domain.Category;
import com.shop.project.dto.CategoryDTO;
import com.shop.project.services.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoryController {

	@Autowired
	private CategoryService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Category> findCategory(@PathVariable Integer id) {
		
		Category cat = service.find(id);
		
		return ResponseEntity.ok().body(cat);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> createCategory(@Valid @RequestBody CategoryDTO catDTO){
		
		Category cat = service.fromDTO(catDTO);
		cat = service.insert(cat);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(cat.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> updateCategory(@Valid @RequestBody CategoryDTO catDTO, @PathVariable Integer id){
		Category cat = service.fromDTO(catDTO);
		
		cat.setId(id);
		
		cat = service.update(cat);
		
		return ResponseEntity.noContent().build();
	}
	

	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Category> deleteCategory(@PathVariable Integer id) {
		
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> findAll() {
		
		List<Category> catList = service.findAll();
		
		List<CategoryDTO> dtoList = catList.stream().map(cat -> new CategoryDTO(cat)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(dtoList);
	}
	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<CategoryDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		Page<Category> pageCat = service.findPage(page, linesPerPage, orderBy, direction);
		
		Page<CategoryDTO> dtoList = pageCat.map(cat -> new CategoryDTO(cat));
		
		return ResponseEntity.ok().body(dtoList);
	}
}
