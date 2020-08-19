package com.shop.project.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.project.daos.ClientDAO;
import com.shop.project.domain.Client;
import com.shop.project.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientDAO dao;
	
	public Client find(Integer id) {
		Optional<Client> cat = dao.findById(id);
		return cat.orElseThrow(() -> new ObjectNotFoundException(
				"Item not found! Id: " + id + ", Type: " + Client.class.getName()));
		
	}
}
