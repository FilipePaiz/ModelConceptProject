package com.shop.project.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shop.project.daos.ClientDAO;
import com.shop.project.domain.Client;
import com.shop.project.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClientDAO clientDao;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	private Random rand = new Random();
	
	@Autowired
	private EmailService emailService;
	
	public void sendNewPassword(String email) {
		Client client = clientDao.findByEmail(email);
		
		if(client == null) {
			throw new ObjectNotFoundException("Email not found");
		}
		
		String newPassword = newPassword();
		
		client.setPassword(pe.encode(newPassword));
		
		clientDao.save(client);
	
		emailService.sendNewPasswordEmail(client, newPassword);
	}

	private String newPassword() {
		char[] vet = new char[10];
		
		for(int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		
		if(opt == 0) {
			return (char) (rand.nextInt(10) + 48);
		}else if(opt == 1) {
			return (char) (rand.nextInt(26) + 65);
		}else {
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
