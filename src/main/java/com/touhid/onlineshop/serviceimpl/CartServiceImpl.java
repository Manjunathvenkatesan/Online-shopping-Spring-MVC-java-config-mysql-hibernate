package com.touhid.onlineshop.serviceimpl;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.touhid.onlineshop.dao.CartDao;
import com.touhid.onlineshop.model.Cart;
import com.touhid.onlineshop.service.CartService;

@Service

public class CartServiceImpl implements CartService{

	@Autowired
	private CartDao cartDao;
	
	
	
	
	@Autowired
	CustomerOrderServiceImpl customerOrderServiceImpl;
	@Transactional()
	@Override
	public void addCart(Cart cart) {
		
	
		cartDao.save(cart);
		
		
	}
	@Transactional(readOnly = true)
	@Override
	public Cart getCartById(long cartId) {
	   
		
		
		return cartDao.findOne(cartId);
	}
	@Transactional(readOnly = true)
	@Override
	public Cart validateCustomer(long cartId) throws IOException {
		
		Cart cart=getCartById(cartId);
		
		if (cart==null || cart.getCartItems().size()==0) {
			
			throw new IOException(cartId+"");
		}
		
		
		  // Cart cart2 = null;
		
			//cart2 = updateCart(cart);
		updateCart(cart);
			
		
		 return getCartById(cartId);
	}
	
	private Cart updateCart(Cart cart) {
		 double grandTotal=0;
		 
		 
		
		grandTotal=customerOrderServiceImpl.getCustomerOrderGrandTotal(cart.getCartId());
		
		cart.setGrandTotal(grandTotal);
		cartDao.save(cart);
		
		
		return cart;
	}

}
