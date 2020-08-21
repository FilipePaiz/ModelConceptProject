package com.shop.project.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.shop.project.services.validation.ClientInsert;

@ClientInsert
public class ClientFullDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotEmpty(message="the field can't be empty")
	@Length(min=5, max=120, message="The size need to be between 5 and 120 characters")
	private String name;
	
	@NotEmpty(message="the field can't be empty")
	@Email(message="Invalid email")
	private String email;
	
	@NotEmpty(message="the field can't be empty")
	private String idCard;
	
	private Integer type;
	
	@NotEmpty(message="the field can't be empty")
	private String password;

	@NotEmpty(message="the field can't be empty")
	private String county;
	
	@NotEmpty(message="the field can't be empty")
	private String number;
	
	private String floor;
	private String neighborhood;
	
	@NotEmpty(message="the field can't be empty")
	private String postalCode;
	
	@NotEmpty(message="the field can't be empty")
	private String phoneOne;
	
	private String phoneTwo;
	private String phoneThree;
	
	private Integer cityId;

	public ClientFullDTO() {
	}

	public ClientFullDTO(String name, String email, String idCard, Integer type, String county, String number,
			String floor, String neighborhood, String postalCode, String phoneOne, String phoneTwo, String phoneThree,
			Integer cityId) {
		super();
		this.name = name;
		this.email = email;
		this.idCard = idCard;
		this.type = type;
		this.county = county;
		this.number = number;
		this.floor = floor;
		this.neighborhood = neighborhood;
		this.postalCode = postalCode;
		this.phoneOne = phoneOne;
		this.phoneTwo = phoneTwo;
		this.phoneThree = phoneThree;
		this.cityId = cityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhoneOne() {
		return phoneOne;
	}

	public void setPhoneOne(String phoneOne) {
		this.phoneOne = phoneOne;
	}

	public String getPhoneTwo() {
		return phoneTwo;
	}

	public void setPhoneTwo(String phoneTwo) {
		this.phoneTwo = phoneTwo;
	}

	public String getPhoneThree() {
		return phoneThree;
	}

	public void setPhoneThree(String phoneThree) {
		this.phoneThree = phoneThree;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	
}
