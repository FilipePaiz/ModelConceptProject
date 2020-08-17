package com.shop.project.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.project.domain.ShopOrder;

@Repository
public interface ShopOrderDAO extends JpaRepository<ShopOrder, Integer> {

}
