package com.udemy.curso.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.curso.domain.State;

@Repository
public interface StateDAO extends JpaRepository<State, Integer> {

}
