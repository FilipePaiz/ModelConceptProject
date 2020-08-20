package com.shop.project.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.shop.project.domain.ShopOrder;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(ShopOrder order) {
		SimpleMailMessage msg = prepareSimpleMailMessageFromOrder(order);
		sendEmail(msg);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromOrder(ShopOrder order) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(order.getClient().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Order confirmed! Code: " + order.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(order.toString());
		return sm;
	}
}
