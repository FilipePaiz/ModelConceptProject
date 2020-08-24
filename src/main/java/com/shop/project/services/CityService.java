package com.shop.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.project.daos.CityDAO;
import com.shop.project.domain.City;

@Service
public class CityService {

	@Autowired
	private CityDAO cityDao;
	
	public List<City> findByState(Integer stateId){
		return cityDao.findCities(stateId);
	}
}
