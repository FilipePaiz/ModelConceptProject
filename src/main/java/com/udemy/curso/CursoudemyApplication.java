package com.udemy.curso;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.udemy.curso.daos.CategoryDAO;
import com.udemy.curso.daos.ProductDAO;
import com.udemy.curso.domain.Category;
import com.udemy.curso.domain.Product;

@SpringBootApplication
public class CursoudemyApplication implements CommandLineRunner {
	
	@Autowired
	private ProductDAO productDao;
	
	@Autowired
	private CategoryDAO categoryDao;

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
		
	}

}
