package com.shop.project.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.project.daos.ShopOrderDAO;
import com.shop.project.domain.ShopOrder;
import com.shop.project.services.exceptions.ObjectNotFoundException;

@Service
public class ShopOrderService {

	@Autowired
	private ShopOrderDAO dao;
	
	public ShopOrder find(Integer id) {
		Optional<ShopOrder> cat = dao.findById(id);
		return cat.orElseThrow(() -> new ObjectNotFoundException(
				"Item not found! Id: " + id + ", Type: " + ShopOrder.class.getName()));
		
	}
}
