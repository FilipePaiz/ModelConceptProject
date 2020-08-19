package com.shop.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.shop.project.daos.AddressDAO;
import com.shop.project.daos.ClientDAO;
import com.shop.project.daos.enums.ClientType;
import com.shop.project.domain.Address;
import com.shop.project.domain.City;
import com.shop.project.domain.Client;
import com.shop.project.dto.ClientDTO;
import com.shop.project.dto.ClientFullDTO;
import com.shop.project.services.exceptions.DataIntegrityException;
import com.shop.project.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientDAO dao;
	
	@Autowired
	private AddressDAO addressDao;
	
	public Client find(Integer id) {
		Optional<Client> cli = dao.findById(id);
		return cli.orElseThrow(() -> new ObjectNotFoundException(
				"Item not found! Id: " + id + ", Type: " + Client.class.getName()));
		
	}
	
	public Client insert(Client cli) {
		cli.setId(null); // just in case
		cli = dao.save(cli);
		
		addressDao.saveAll(cli.getAddresses());
		
		return cli;
	}
	
	public Client update(Client cli) {
		Client newCli = find(cli.getId());
		updateData(newCli, cli);
		return dao.save(newCli);
	}
	
	private void updateData(Client newCli, Client cli) {
		newCli.setName(cli.getName());
		newCli.setEmail(cli.getEmail());
		
	}

	public void delete(Integer id) {
		
		find(id);
		try {
			dao.deleteById(id);	
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Not possible to delete because of products and addresses");
		}
		
	}
	
	public List<Client> findAll(){
		return dao.findAll();
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageReq = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return dao.findAll(pageReq);
	}
	
	public Client fromDTO(ClientDTO cliDTO) {
		return new Client(cliDTO.getId(), cliDTO.getName(), cliDTO.getEmail(), null, null);
	}
	
	public Client fromDTO(ClientFullDTO cliDTO) {
		 Client client = new Client(null, cliDTO.getName(), cliDTO.getEmail(), cliDTO.getIdCard(), ClientType.toEnum(cliDTO.getType()));
		 City city = new City(cliDTO.getCityId(), null, null);
		 Address address = new Address(null, cliDTO.getCounty(), cliDTO.getNumber(), cliDTO.getFloor(), cliDTO.getNeighborhood(), cliDTO.getPostalCode(), client, city);
		 
		 client.getAddresses().add(address);
		 client.getPhone().add(cliDTO.getPhoneOne());
		 
		 if(cliDTO.getPhoneTwo() != null) {
			 client.getPhone().add(cliDTO.getPhoneTwo());
		 }
		 if(cliDTO.getPhoneThree() != null) {
			 client.getPhone().add(cliDTO.getPhoneTwo());
		 }
		 
		 return client;
	}
}
