package com.shop.project.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.shop.project.domain.Category;

public class CategoryDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="the field can't be empty")
	@Length(min=5, max=80, message="The size need to be between 5 and 80 characters")
	private String name;
	
	public CategoryDTO() {
		
	}

	public CategoryDTO(Category cat) {
		this.id = cat.getId();
		this.name = cat.getName();
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
	
	
}
