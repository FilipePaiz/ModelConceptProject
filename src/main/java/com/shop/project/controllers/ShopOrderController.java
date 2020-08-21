package com.shop.project.controllers;

import java.net.URI;

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

import com.shop.project.domain.ShopOrder;
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
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ShopOrder>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="timeStamp") String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC") String direction) {
		
		Page<ShopOrder> pageShopOrder = service.findPage(page, linesPerPage, orderBy, direction);
		
		return ResponseEntity.ok().body(pageShopOrder);
	}
}
