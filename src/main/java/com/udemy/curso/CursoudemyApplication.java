package com.udemy.curso;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.udemy.curso.daos.CategoryDAO;
import com.udemy.curso.domain.Category;

@SpringBootApplication
public class CursoudemyApplication implements CommandLineRunner {
	
	@Autowired
	private CategoryDAO categoryDao;

	public static void main(String[] args) {
		SpringApplication.run(CursoudemyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Computadores");
		Category cat2 = new Category(null, "Monitores");
		
		categoryDao.saveAll(Arrays.asList(cat1, cat2));
	}

}
