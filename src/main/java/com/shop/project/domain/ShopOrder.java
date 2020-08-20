package com.shop.project.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class ShopOrder implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern="dd/MM/yyy HH:mm")
	private Date timeStamp;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="order")
	private Payment payment;
	
	@ManyToOne
	@JoinColumn(name="ClientId")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name="OrderAdressId")
	private Address deliverAddress;
	
	@OneToMany(mappedBy="id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	public ShopOrder() {
		
	}

	public ShopOrder(Integer id, Date timeStamp, Client client, Address deliverAddress) {
		super();
		this.id = id;
		this.timeStamp = timeStamp;
		this.client = client;
		this.deliverAddress = deliverAddress;
	}
	
	public double getTotalValue() {
		double sum = 0.0;
		
		for(OrderItem ip : items) {
			sum = sum + ip.getSubTotal();
		}
		
		return sum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Address getDeliverAddress() {
		return deliverAddress;
	}

	public void setDeliverAddress(Address deliverAddress) {
		this.deliverAddress = deliverAddress;
	}
	
	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShopOrder other = (ShopOrder) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "PT"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		StringBuilder builder = new StringBuilder();
		builder.append("Order number: ");
		builder.append(getId());
		builder.append(", Created: ");
		builder.append(sdf.format(getTimeStamp()));
		builder.append(", Client: ");
		builder.append(getClient().getName());
		builder.append(", Payment status: ");
		builder.append(getPayment().getState().getDescription());
		builder.append("\nDetails:\n");
		for (OrderItem orderItem : getItems()) {
			builder.append(orderItem.toString());
		}
		builder.append("Total value: ");
		builder.append(nf.format(getTotalValue()));
		
		return builder.toString();
	}
	
	
}
