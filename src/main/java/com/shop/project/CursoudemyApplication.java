package com.shop.project;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.shop.project.daos.AddressDAO;
import com.shop.project.daos.CategoryDAO;
import com.shop.project.daos.CityDAO;
import com.shop.project.daos.ClientDAO;
import com.shop.project.daos.OrderItemDAO;
import com.shop.project.daos.PaymentDAO;
import com.shop.project.daos.ProductDAO;
import com.shop.project.daos.ShopOrderDAO;
import com.shop.project.daos.StateDAO;
import com.shop.project.daos.enums.ClientType;
import com.shop.project.daos.enums.PaymentStatus;
import com.shop.project.domain.Address;
import com.shop.project.domain.Category;
import com.shop.project.domain.City;
import com.shop.project.domain.Client;
import com.shop.project.domain.OrderItem;
import com.shop.project.domain.Payment;
import com.shop.project.domain.PaymentWithCard;
import com.shop.project.domain.PaymentWithCheck;
import com.shop.project.domain.Product;
import com.shop.project.domain.ShopOrder;
import com.shop.project.domain.State;

@SpringBootApplication
public class CursoudemyApplication implements CommandLineRunner {
	
	@Autowired
	private CategoryDAO categoryDao;	
	@Autowired
	private ProductDAO productDao;
	@Autowired
	private StateDAO stateDao;	
	@Autowired
	private CityDAO cityDao;
	@Autowired
	private ClientDAO clientDao;
	@Autowired
	private AddressDAO addressDao;
	@Autowired
	private ShopOrderDAO shopOrderDao;
	@Autowired
	private PaymentDAO paymentDao;
	@Autowired
	private OrderItemDAO orderItemDao;
	

	public static void main(String[] args) {
		SpringApplication.run(CursoudemyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Computadores");
		Category cat2 = new Category(null, "Monitores");
		
		Product p1 = new Product(null, "LeNovo 2020", 2000d);
		Product p2 = new Product(null, "AOC 2019", 400d);
		Product p3 = new Product(null, "HP 2018", 1400d);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		categoryDao.saveAll(Arrays.asList(cat1, cat2));
		productDao.saveAll(Arrays.asList(p1, p2, p3));
		
		State st1 = new State(null, "Texas");
		State st2 = new State(null, "Califórnia");
		
		City c1 = new City(null, "Austin", st1);
		City c2 = new City(null, "Sacramento", st2);
		City c3 = new City(null, "San Diego", st2);
		
		st1.getCities().addAll(Arrays.asList(c1));
		st2.getCities().addAll(Arrays.asList(c2, c3));
		
		stateDao.saveAll(Arrays.asList(st1, st2));
		cityDao.saveAll(Arrays.asList(c1, c2, c3));
		
		Client cli1 = new Client(null, "André", "andre@gmail.com", "123456789", ClientType.INDIVIDUALPERSON);
		cli1.getPhone().addAll(Arrays.asList("147882369", "963852741"));
		
		Address ad1 = new Address(null, "Eevee Street", "300", "Ap 303", "Garden", "1345678", cli1, c1);
		Address ad2 = new Address(null, "Kel Street", "105", "Ap 800", "Center", "741852", cli1, c2);
		
		cli1.getAddresses().addAll(Arrays.asList(ad1, ad2));
		
		clientDao.saveAll(Arrays.asList(cli1));
		addressDao.saveAll(Arrays.asList(ad1, ad2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		ShopOrder ord1 = new ShopOrder(null, sdf.parse("30/09/2017 10:32"), cli1, ad1);
		ShopOrder ord2 = new ShopOrder(null, sdf.parse("10/10/2017 19:35"), cli1, ad2);
		
		Payment paym1 = new PaymentWithCard(null, PaymentStatus.DONE, ord1, 6);
		ord1.setPayment(paym1);
		
		Payment paym2 = new PaymentWithCheck(null, PaymentStatus.WAITING, ord2, sdf.parse("20/10/2017 00:00"), null);
		ord2.setPayment(paym2);
		
		cli1.getOrders().addAll(Arrays.asList(ord1, ord2));
		
		shopOrderDao.saveAll(Arrays.asList(ord1, ord2));
		paymentDao.saveAll(Arrays.asList(paym1, paym2));
		
		OrderItem oi1 = new OrderItem(ord1, p1, 0.00, 1, 2000.00);
		OrderItem oi2 = new OrderItem(ord1, p3, 0.00, 2, 80.00);
		OrderItem oi3 = new OrderItem(ord2, p2, 100.00, 1, 800.00);
		
		ord1.getItems().addAll(Arrays.asList(oi1, oi2));
		ord2.getItems().addAll(Arrays.asList(oi3));
		
		p1.getItems().addAll(Arrays.asList(oi1));
		p2.getItems().addAll(Arrays.asList(oi3));
		p3.getItems().addAll(Arrays.asList(oi2));
		
		orderItemDao.saveAll(Arrays.asList(oi1,oi2,oi3));
		
	}

}
