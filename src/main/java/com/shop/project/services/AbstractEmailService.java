package com.shop.project.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.shop.project.domain.Client;
import com.shop.project.domain.ShopOrder;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
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
	
	protected String htmlFromTemplateOrder(ShopOrder order) {
		Context context = new Context();
		context.setVariable("order", order);
		
		return templateEngine.process("email/emailConfirmation", context);
				
	}
	
	@Override
	 public void sendOrderConfirmationHtmlEmail(ShopOrder order) {		
		try {
			MimeMessage mm; mm = prepareMimeMessageFromOrder(order);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(order);
		}
	}

	private MimeMessage prepareMimeMessageFromOrder(ShopOrder order) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(order.getClient().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Order confirmed! Code: " + order.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateOrder(order), true);
		
		return mimeMessage;
	}
	
	@Override
	public void sendNewPasswordEmail(Client client,String newPassword) {
		SimpleMailMessage msg = prepareNewPasswordEmail(client, newPassword);
		sendEmail(msg);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Client client, String newPassword) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(client.getEmail());
		sm.setFrom(sender);
		sm.setSubject("New password confirmation");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("New password: " + newPassword);
		return sm;
	}
}
