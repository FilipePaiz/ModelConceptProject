package com.shop.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.project.daos.StateDAO;
import com.shop.project.domain.State;

@Service
public class StateService {

	@Autowired
	private StateDAO stateDao;
	
	public List<State> findAll(){
		return stateDao.findAllByOrderByName();
	}
}
