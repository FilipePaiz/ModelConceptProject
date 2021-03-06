package com.shop.project.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.project.domain.Address;

@Repository
public interface AddressDAO extends JpaRepository<Address, Integer> {

}
