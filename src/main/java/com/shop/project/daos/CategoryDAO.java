package com.shop.project.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.project.domain.Category;

@Repository
public interface CategoryDAO extends JpaRepository<Category, Integer> {

}
