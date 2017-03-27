package com.touhid.onlineshop.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class CustomerOrder implements Serializable{

	private static final long serialVersionUID = 9L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long customerOrderId;

	@OneToOne
	@JoinColumn(name="cartId")
	private Cart cart;
	
	@OneToOne
	@JoinColumn(name="cutomerId")
	private Customer customer;
	
	@OneToOne
	@JoinColumn(name="shippingAddressId")
	private ShippingAddress shippingAddress;
	
	
	@OneToOne
	@JoinColumn(name="billingAddressId")
	private BillingAddress billingAddress;


	


	public long getCustomerOrderId() {
		return customerOrderId;
	}


	public void setCustomerOrderId(long customerOrderId) {
		this.customerOrderId = customerOrderId;
	}


	public Cart getCart() {
		return cart;
	}


	public void setCart(Cart cart) {
		this.cart = cart;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}


	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}


	public BillingAddress getBillingAddress() {
		return billingAddress;
	}


	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}
	
	
	
	
	
}
