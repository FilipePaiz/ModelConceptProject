package com.shop.project.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shop.project.daos.enums.PaymentStatus;

@Entity
public class PaymentWithCheck extends Payment {
	
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern="dd/MM/yyy")
	private Date limitDate;
	@JsonFormat(pattern="dd/MM/yyy")
	private Date paymentDate;

	public PaymentWithCheck() {
		
	}

	public PaymentWithCheck(Integer id, PaymentStatus state, ShopOrder order,  Date limitDate, Date paymentDate) {
		super(id, state, order);
		this.limitDate = limitDate;
		this.paymentDate = paymentDate;
		// TODO Auto-generated constructor stub
	}

	public Date getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	
}
