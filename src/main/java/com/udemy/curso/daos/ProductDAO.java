package com.udemy.curso.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.curso.domain.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer>{

}
