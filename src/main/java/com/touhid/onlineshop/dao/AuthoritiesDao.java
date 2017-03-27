package com.touhid.onlineshop.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.touhid.onlineshop.model.Authorities;

@Repository

public interface AuthoritiesDao extends CrudRepository<Authorities, Long>{

	Authorities findAuthoritiesByusername(String username);
}
