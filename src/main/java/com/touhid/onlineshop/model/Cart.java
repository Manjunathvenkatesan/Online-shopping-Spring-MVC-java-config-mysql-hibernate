package com.touhid.onlineshop.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cart implements Serializable{

	
	
	
	private static final long serialVersionUID = 3L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long cartId;
	
	@OneToMany(mappedBy="cart",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	
	private List<CartItem> cartItems;
	
	
	@OneToOne
	@JoinColumn(name="customerId")
	@JsonIgnore
	private Customer  customer;
	
	private double grandTotal;
	
	
	
	
	



	public long getCartId() {
		return cartId;
	}



	public void setCartId(long cartId) {
		this.cartId = cartId;
	}



	public List<CartItem> getCartItems() {
		return cartItems;
	}



	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}



	public Customer getCustomer() {
		return customer;
	}



	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	public double getGrandTotal() {
		return grandTotal;
	}



	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}



	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", cartItems=" + cartItems + ", customer=" + customer + ", grandTotal="
				+ grandTotal + "]";
	}



	



	
	
	
}
