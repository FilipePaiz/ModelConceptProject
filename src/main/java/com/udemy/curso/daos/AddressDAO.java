package com.udemy.curso.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.curso.domain.Address;

@Repository
public interface AddressDAO extends JpaRepository<Address, Integer> {

}
