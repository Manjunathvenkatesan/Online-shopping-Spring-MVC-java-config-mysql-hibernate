package com.touhid.onlineshop.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.touhid.onlineshop.dao.BillingAddressDao;
import com.touhid.onlineshop.model.BillingAddress;
import com.touhid.onlineshop.service.BillingAddressService;

@Service

public class BillingAddressServiceImpl implements BillingAddressService{

	@Autowired
	private BillingAddressDao billingAddressDao;
	
	//persisting and deleting objects requires a transaction in JPA. Thus we need to make sure a transaction is running, which we do by having the method annotated with @Transactional.
	@Transactional()
	@Override
	public void addBillingAddress(BillingAddress billingAddress) {
		
		billingAddressDao.save(billingAddress);
		
	}

	@Transactional(readOnly = true)
	@Override
	public BillingAddress getBillingAddressbyId(long billingAddressId) {
		
		return billingAddressDao.findOne(billingAddressId);
	}

}
