package com.shop.project.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shop.project.domain.Category;
import com.shop.project.domain.ShopOrder;
import com.shop.project.dto.CategoryDTO;
import com.shop.project.services.ShopOrderService;

@RestController
@RequestMapping(value="/orders")
public class ShopOrderController {

	@Autowired
	private ShopOrderService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<ShopOrder> find(@PathVariable Integer id) {
		
		ShopOrder so = service.find(id);
		
		return ResponseEntity.ok().body(so);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> createCategory(@Valid @RequestBody ShopOrder shopOrder){
		
		shopOrder = service.insert(shopOrder);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(shopOrder.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
