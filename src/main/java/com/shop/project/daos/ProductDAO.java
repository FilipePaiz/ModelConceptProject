package com.shop.project.daos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.project.domain.Category;
import com.shop.project.domain.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer>{

	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cat WHERE obj.name LIKE %:name% AND cat IN :categories")
	Page<Product> search(@Param("name") String name,@Param("categories") List<Category> categories, Pageable pageReq);
	
	//ou mudando o nome do method para findDistinctByNameContainingAndCategoriesIn vai fazer o mesmo que a anotação e os @param fazem. Fica mais clean
	//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories

}
