package com.touhid.onlineshop.service;

import com.touhid.onlineshop.model.BillingAddress;

public interface BillingAddressService {

	public void addBillingAddress(BillingAddress billingAddress);
	
	BillingAddress getBillingAddressbyId(long billingAddressId);
}
