package com.udemy.curso.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.curso.domain.ShopOrder;

@Repository
public interface ShopOrderDAO extends JpaRepository<ShopOrder, Integer> {

}
