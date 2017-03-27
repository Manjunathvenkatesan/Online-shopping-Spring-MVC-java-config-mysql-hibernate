package com.touhid.onlineshop.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.touhid.onlineshop.dao.CustomerOrderDao;
import com.touhid.onlineshop.model.Cart;
import com.touhid.onlineshop.model.CartItem;
import com.touhid.onlineshop.model.CustomerOrder;
import com.touhid.onlineshop.service.CartService;
import com.touhid.onlineshop.service.CustomerOrderService;

@Service

public class CustomerOrderServiceImpl implements CustomerOrderService{

	@Autowired
	private CustomerOrderDao customerOrderDao;
	
	@Autowired
	private CartService cartService;
	@Transactional()
	@Override
	public void addCustomerOrder(CustomerOrder customerOrder) {
		
		Cart cart=customerOrder.getCart();
		
		CustomerOrder oldCustomerOrder=customerOrderDao.getCustomerOrderBycart(cart);
		if (oldCustomerOrder!=null) {
			
			
			
			oldCustomerOrder.setCustomer(cart.getCustomer());
			oldCustomerOrder.setShippingAddress(cart.getCustomer().getShippingAddress());
			oldCustomerOrder.setBillingAddress(cart.getCustomer().getBillingAddress());
			customerOrderDao.save(oldCustomerOrder);
			
		}
		
		else {
			customerOrder.setCustomer(cart.getCustomer());
			customerOrder.setShippingAddress(cart.getCustomer().getShippingAddress());
			customerOrder.setBillingAddress(cart.getCustomer().getBillingAddress());
			
			
			
			
			customerOrderDao.save(customerOrder);
		}
		
		
		
		
		
	}
	@Transactional(readOnly = true)
	@Override
	public double getCustomerOrderGrandTotal(long cartId) {
	
		double grandTotal=0;
		Cart cart=cartService.getCartById(cartId);
		
		
		List<CartItem> cartItems=cart.getCartItems();
		
		for (CartItem cartItem : cartItems) {
			grandTotal+=cartItem.getTotalPrice();
		}
		
		
		return grandTotal;
	}
	@Transactional(readOnly = true)
	@Override
	public CustomerOrder getCustomerOrderBycart(Cart cart) {
		
		return customerOrderDao.getCustomerOrderBycart(cart);
	}
	@Transactional(readOnly = true)
	@Override
	public List<CustomerOrder> getAllCustomerOrder() {
		
		return (List<CustomerOrder>) customerOrderDao.findAll();
	}
	@Transactional()
	@Override
	public void deleteCustomerOrderById(long customerOrderId) {
		
		customerOrderDao.delete(customerOrderId);
		
	}
	@Transactional(readOnly = true)
	@Override
	public CustomerOrder getCustomerOrderById(long customerOrderId) {
		
		return customerOrderDao.findOne(customerOrderId);
	}
	

}
