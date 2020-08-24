package com.shop.project.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.project.domain.State;

@Repository
public interface StateDAO extends JpaRepository<State, Integer> {

	@Transactional(readOnly=true)
	public List<State> findAllByOrderByName();
	
	
}
