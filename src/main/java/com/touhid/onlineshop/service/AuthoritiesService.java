package com.touhid.onlineshop.service;

import com.touhid.onlineshop.model.Authorities;

public interface AuthoritiesService {

	public void addAuthorities(Authorities authorities);
	
	Authorities findAuthoritiesByusername(String username);
}
