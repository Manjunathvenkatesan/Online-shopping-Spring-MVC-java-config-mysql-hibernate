package com.touhid.onlineshop.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.touhid.onlineshop.model.CustomerContact;

@Repository
public interface CustomerContactDao extends CrudRepository<CustomerContact, Long>,PagingAndSortingRepository<CustomerContact, Long>{

}
