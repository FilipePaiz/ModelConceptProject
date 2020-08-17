package com.shop.project.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.project.domain.Client;

@Repository
public interface ClientDAO extends JpaRepository<Client, Integer> {

}
