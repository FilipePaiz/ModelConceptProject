package com.udemy.curso.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.curso.domain.Client;
import com.udemy.curso.services.ClientService;

@RestController
@RequestMapping(value="/clients")
public class ClientController {

	@Autowired
	private ClientService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Client cat = service.search(id);
		
		return ResponseEntity.ok().body(cat);
	}
}
