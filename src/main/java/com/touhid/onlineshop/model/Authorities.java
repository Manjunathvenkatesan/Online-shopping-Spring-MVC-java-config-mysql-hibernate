package com.touhid.onlineshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Authorities {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long authoritiesId;
	
	private String username;
	
	private String authorityType;

	public long getAuthoritiesId() {
		return authoritiesId;
	}

	public void setAuthoritiesId(long authoritiesId) {
		this.authoritiesId = authoritiesId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthorityType() {
		return authorityType;
	}

	public void setAuthorityType(String authorityType) {
		this.authorityType = authorityType;
	}
	
	
	
	
}
