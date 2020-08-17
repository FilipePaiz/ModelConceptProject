package com.shop.project.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.project.domain.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer>{

}
