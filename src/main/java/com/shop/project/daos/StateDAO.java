package com.shop.project.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.project.domain.State;

@Repository
public interface StateDAO extends JpaRepository<State, Integer> {

}
