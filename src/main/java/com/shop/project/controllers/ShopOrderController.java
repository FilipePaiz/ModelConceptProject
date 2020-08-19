package com.shop.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shop.project.domain.ShopOrder;
import com.shop.project.services.ShopOrderService;

@RestController
@RequestMapping(value="/orders")
public class ShopOrderController {

	@Autowired
	private ShopOrderService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<ShopOrder> find(@PathVariable Integer id) {
		
		ShopOrder cat = service.find(id);
		
		return ResponseEntity.ok().body(cat);
	}
}
