package com.shop.project.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.project.daos.OrderItemDAO;
import com.shop.project.daos.PaymentDAO;
import com.shop.project.daos.ShopOrderDAO;
import com.shop.project.daos.enums.PaymentStatus;
import com.shop.project.domain.Client;
import com.shop.project.domain.OrderItem;
import com.shop.project.domain.PaymentWithCheck;
import com.shop.project.domain.ShopOrder;
import com.shop.project.security.UserSS;
import com.shop.project.services.exceptions.AuthorizationException;
import com.shop.project.services.exceptions.ObjectNotFoundException;

@Service
public class ShopOrderService {

	@Autowired
	private ShopOrderDAO dao;
	
	@Autowired
	private PaymentCheckService paymentCheckService;
	
	@Autowired
	private PaymentDAO paymentDao;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderItemDAO orderItemDao;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private EmailService emailService;
	
	public ShopOrder find(Integer id) {
		Optional<ShopOrder> cat = dao.findById(id);
		return cat.orElseThrow(() -> new ObjectNotFoundException(
				"Item not found! Id: " + id + ", Type: " + ShopOrder.class.getName()));
		
	}
	
	@Transactional
	public ShopOrder insert(ShopOrder order) {
		order.setId(null);
		order.setTimeStamp(new Date());
		order.setClient(clientService.find(order.getClient().getId()));
		order.getPayment().setState(PaymentStatus.WAITING);
		order.getPayment().setOrder(order);
		
		if(order.getPayment() instanceof PaymentWithCheck) {
			PaymentWithCheck payCheck = (PaymentWithCheck) order.getPayment();
			paymentCheckService.fullfillPaymentCheck(payCheck, order.getTimeStamp());
		}
		
		order = dao.save(order);
		paymentDao.save(order.getPayment());
		
		for(OrderItem oi : order.getItems()) {
			oi.setDiscount(0.0);
			oi.setProduct(productService.find(oi.getProduct().getId()));
			oi.setPrice(oi.getProduct().getPrice());
			oi.setOrder(order);
		}
		orderItemDao.saveAll(order.getItems());
		emailService.sendOrderConfirmationHtmlEmail(order);
		return order;
		
	}
	
	public Page<ShopOrder> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		UserSS user= UserService.authenticated();
		
		if(user == null) {
			throw new AuthorizationException("Access not allowed");
		}
		
		PageRequest pageReq = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Client client = clientService.find(user.getId());
		return dao.findByClient(client, pageReq);
	}
}
