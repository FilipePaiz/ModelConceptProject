package com.shop.project.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.shop.project.domain.ShopOrder;

public interface EmailService {

	void sendOrderConfirmationEmail(ShopOrder order);
	
	void sendEmail(SimpleMailMessage msg);
	
	
	void sendOrderConfirmationHtmlEmail(ShopOrder order);
	
	void sendHtmlEmail(MimeMessage msg);
}
