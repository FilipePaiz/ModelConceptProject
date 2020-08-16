package com.udemy.curso;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.udemy.curso.daos.CategoryDAO;
import com.udemy.curso.daos.CityDAO;
import com.udemy.curso.daos.ProductDAO;
import com.udemy.curso.daos.StateDAO;
import com.udemy.curso.domain.Category;
import com.udemy.curso.domain.City;
import com.udemy.curso.domain.Product;
import com.udemy.curso.domain.State;

@SpringBootApplication
public class CursoudemyApplication implements CommandLineRunner {
	
	@Autowired
	private CategoryDAO categoryDao;	
	@Autowired
	private ProductDAO productDao;
	@Autowired
	private StateDAO stateDao;	
	@Autowired
	private CityDAO cityDao;

	public static void main(String[] args) {
		SpringApplication.run(CursoudemyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Computadores");
		Category cat2 = new Category(null, "Monitores");
		
		Product p1 = new Product(null, "LeNovo 2020", 2000d);
		Product p2 = new Product(null, "AOC 2019", 400d);
		Product p3 = new Product(null, "HP 2018", 1400d);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		categoryDao.saveAll(Arrays.asList(cat1, cat2));
		productDao.saveAll(Arrays.asList(p1, p2, p3));
		
		State st1 = new State(null, "Texas");
		State st2 = new State(null, "Califórnia");
		
		City c1 = new City(null, "Austin", st1);
		City c2 = new City(null, "Sacramento", st2);
		City c3 = new City(null, "San Diego", st2);
		
		st1.getCities().addAll(Arrays.asList(c1));
		st2.getCities().addAll(Arrays.asList(c2, c3));
		
		stateDao.saveAll(Arrays.asList(st1, st2));
		cityDao.saveAll(Arrays.asList(c1, c2, c3));
		
	}

}
