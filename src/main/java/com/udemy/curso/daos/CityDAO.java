package com.udemy.curso.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.curso.domain.City;

@Repository
public interface CityDAO extends JpaRepository<City, Integer> {

}
