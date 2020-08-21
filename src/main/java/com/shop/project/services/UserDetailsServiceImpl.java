package com.shop.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shop.project.daos.ClientDAO;
import com.shop.project.domain.Client;
import com.shop.project.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private ClientDAO clientDao;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Client client = clientDao.findByEmail(email);
		
		if(client == null) {
			throw new UsernameNotFoundException(email);
		}
		
		
		
		return new UserSS(client.getId(), client.getEmail(), client.getPassword(), client.getProfile());
	}

}
