package com.udemy.curso.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.curso.domain.Client;

@Repository
public interface ClientDAO extends JpaRepository<Client, Integer> {

}
