package com.shop.project.dto;

import java.io.Serializable;

import com.shop.project.domain.Product;

public class ProductDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private Double price;
	
	public ProductDTO() {
		
	}
	
	public ProductDTO(Product prod) {
		this.id = prod.getId();
		this.name = prod.getName();
		this.price = prod.getPrice();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	

}
