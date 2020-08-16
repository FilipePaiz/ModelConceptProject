package com.udemy.curso.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.udemy.curso.domain.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{

}
