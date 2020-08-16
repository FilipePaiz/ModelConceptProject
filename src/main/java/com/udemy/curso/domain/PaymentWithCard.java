package com.udemy.curso.domain;

import javax.persistence.Entity;

import com.udemy.curso.daos.enums.PaymentStatus;

@Entity
public class PaymentWithCard extends Payment{
	
	private static final long serialVersionUID = 1L;

	private Integer cardNumber;
	
	public PaymentWithCard() {
		
	}

	public PaymentWithCard(Integer id, PaymentStatus state, ShopOrder order, Integer cardNumber) {
		super(id, state, order);
		this.cardNumber = cardNumber;
		// TODO Auto-generated constructor stub
	}

	public Integer getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Integer cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	
}
