package com.udemy.curso.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.curso.daos.ClientDAO;
import com.udemy.curso.domain.Client;
import com.udemy.curso.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientDAO dao;
	
	public Client search(Integer id) {
		Optional<Client> cat = dao.findById(id);
		return cat.orElseThrow(() -> new ObjectNotFoundException(
				"Item not found! Id: " + id + ", Type: " + Client.class.getName()));
		
	}
}
