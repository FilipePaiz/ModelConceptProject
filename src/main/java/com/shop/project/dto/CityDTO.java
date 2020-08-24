package com.shop.project.dto;

import javax.validation.constraints.NotEmpty;

import com.shop.project.domain.City;

public class CityDTO {

	
	private Integer id;
	
	@NotEmpty(message="the field can't be empty")
	private String name;

	public CityDTO() {
		
	}
	
	public CityDTO(City city) {
		super();
		this.name = city.getName();
		this.id = city.getId();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
