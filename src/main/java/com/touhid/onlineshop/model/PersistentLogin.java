package com.touhid.onlineshop.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

@Entity
@Table(name="persistent_logins")
public class PersistentLogin {

	@Column(nullable=false,length=64)
	private String username;
	
	@Id
	@Column(nullable=false,length=64)
	private String series;
	
	@Column(nullable=false,length=64)
	private String token;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_used",nullable=false)
	private Date date;

	
	public PersistentLogin() {
		
	}
	
	public PersistentLogin(PersistentRememberMeToken token){
		
		        this.series = token.getSeries();
		
		        this.username = token.getUsername();
		
		        this.token = token.getTokenValue();
		
		        this.date = token.getDate();
		
		    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
