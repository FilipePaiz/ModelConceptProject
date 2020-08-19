package com.shop.project.controllers;

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

import com.shop.project.domain.Client;
import com.shop.project.dto.ClientDTO;
import com.shop.project.services.ClientService;

@RestController
@RequestMapping(value="/clients")
public class ClientController {

	@Autowired
	private ClientService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Client> find(@PathVariable Integer id) {
		
		Client cli = service.find(id);
		
		return ResponseEntity.ok().body(cli);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> updateClient(@Valid @RequestBody ClientDTO catDTO, @PathVariable Integer id){
		Client cli = service.fromDTO(catDTO);
		
		cli.setId(id);
		
		cli = service.update(cli);
		
		return ResponseEntity.noContent().build();
	}
	

	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Client> deleteClient(@PathVariable Integer id) {
		
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClientDTO>> findAll() {
		
		List<Client> cliList = service.findAll();
		
		List<ClientDTO> dtoList = cliList.stream().map(cat -> new ClientDTO(cat)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(dtoList);
	}
	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		Page<Client> pageCli = service.findPage(page, linesPerPage, orderBy, direction);
		
		Page<ClientDTO> dtoList = pageCli.map(cat -> new ClientDTO(cat));
		
		return ResponseEntity.ok().body(dtoList);
	}
}
