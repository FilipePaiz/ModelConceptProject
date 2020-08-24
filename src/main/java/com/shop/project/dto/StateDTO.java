package com.shop.project.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.shop.project.domain.State;

public class StateDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotEmpty(message="the field can't be empty")
	private String name;
	
	public StateDTO() {
		
	}
	
	public StateDTO(State state) {
		super();
		this.name = state.getName();
		this.id = state.getId();
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
