package com.touhid.onlineshop.service;

import com.touhid.onlineshop.model.Users;

public interface UsersService {

	public void addUsers(Users users);
	
	Users findUserByusername(String username);
}
