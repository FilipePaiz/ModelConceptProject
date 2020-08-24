package com.shop.project.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shop.project.daos.AddressDAO;
import com.shop.project.daos.ClientDAO;
import com.shop.project.daos.enums.ClientType;
import com.shop.project.daos.enums.Profile;
import com.shop.project.domain.Address;
import com.shop.project.domain.City;
import com.shop.project.domain.Client;
import com.shop.project.dto.ClientDTO;
import com.shop.project.dto.ClientFullDTO;
import com.shop.project.security.UserSS;
import com.shop.project.services.exceptions.AuthorizationException;
import com.shop.project.services.exceptions.DataIntegrityException;
import com.shop.project.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientDAO dao;

	@Autowired
	private AddressDAO addressDao;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imgService;

	@Value("${img.prefix.client.profile}")
	private String imagePrefix;
	
	@Value("${img.profile.size}")
	private int imageSize;
	
	public Client find(Integer id) {
		UserSS user = UserService.authenticated();

		if (user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Access not allowed");
		}

		Optional<Client> cli = dao.findById(id);
		return cli.orElseThrow(
				() -> new ObjectNotFoundException("Item not found! Id: " + id + ", Type: " + Client.class.getName()));

	}
	
	public Client findByEmail(String email) {
		UserSS user = UserService.authenticated();
		
		if (user == null || !user.hasRole(Profile.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Access not allowed");
		}
		
		Client client = dao.findByEmail(email);
		
		if(client == null) {
			throw new ObjectNotFoundException("Item not found! Id: " + user.getId() + ", Type: " + Client.class.getName());
		}
		
		return client;
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
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Not possible to delete because of products and addresses");
		}

	}

	public List<Client> findAll() {
		return dao.findAll();
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageReq = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return dao.findAll(pageReq);
	}

	public Client fromDTO(ClientDTO cliDTO) {
		return new Client(cliDTO.getId(), cliDTO.getName(), cliDTO.getEmail(), null, null, null);
	}

	public Client fromDTO(ClientFullDTO cliDTO) {
		Client client = new Client(null, cliDTO.getName(), cliDTO.getEmail(), cliDTO.getIdCard(),
				ClientType.toEnum(cliDTO.getType()), pe.encode(cliDTO.getPassword()));
		City city = new City(cliDTO.getCityId(), null, null);
		Address address = new Address(null, cliDTO.getCounty(), cliDTO.getNumber(), cliDTO.getFloor(),
				cliDTO.getNeighborhood(), cliDTO.getPostalCode(), client, city);

		client.getAddresses().add(address);
		client.getPhone().add(cliDTO.getPhoneOne());

		if (cliDTO.getPhoneTwo() != null) {
			client.getPhone().add(cliDTO.getPhoneTwo());
		}
		if (cliDTO.getPhoneThree() != null) {
			client.getPhone().add(cliDTO.getPhoneTwo());
		}

		return client;
	}

	public URI uploadProfilePicture(MultipartFile multipartFile) {
		
		UserSS user = UserService.authenticated();
		
		if(user == null) {
			throw new AuthorizationException("Access not allowed");
		}
		
		BufferedImage jgpImage = imgService.getJpgImageFromFile(multipartFile);
		jgpImage = imgService.cropASquare(jgpImage);
		jgpImage = imgService.resize(jgpImage, imageSize);
		String fileName = imagePrefix + user.getId() + ".jpg";
		
		return s3Service.uploadFile(imgService.getInputStream(jgpImage, "jpg"), fileName, "image");
	}
	
}
