package com.udemy.curso.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.curso.domain.Category;

@Repository
public interface CategoryDAO extends JpaRepository<Category, Integer> {

}
