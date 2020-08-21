package com.shop.project.daos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.project.domain.Client;
import com.shop.project.domain.ShopOrder;

@Repository
public interface ShopOrderDAO extends JpaRepository<ShopOrder, Integer> {

	@Transactional(readOnly=true)
	Page<ShopOrder> findByClient(Client client, Pageable pageRequest);
}
