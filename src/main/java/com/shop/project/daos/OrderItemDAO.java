package com.shop.project.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.project.domain.OrderItem;

@Repository
public interface OrderItemDAO extends JpaRepository<OrderItem, Integer> {

}
