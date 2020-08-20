package com.shop.project.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.shop.project.domain.PaymentWithCheck;

@Service
public class PaymentCheckService {

	
	public void fullfillPaymentCheck(PaymentWithCheck payCheck, Date orderTimeStamp) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(orderTimeStamp);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		payCheck.setPaymentDate(cal.getTime());
	}
}
