package com.touhid.onlineshop.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.touhid.onlineshop.model.Cart;
import com.touhid.onlineshop.model.CustomerOrder;

@Repository

public interface CustomerOrderDao extends CrudRepository<CustomerOrder, Long>{

	CustomerOrder getCustomerOrderBycart(Cart cart);
}
