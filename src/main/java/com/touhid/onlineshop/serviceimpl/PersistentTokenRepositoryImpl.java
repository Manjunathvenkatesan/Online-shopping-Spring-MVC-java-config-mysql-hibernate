package com.touhid.onlineshop.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.touhid.onlineshop.dao.RememberMeTokenRepository;
import com.touhid.onlineshop.model.PersistentLogin;


@Service

public class PersistentTokenRepositoryImpl implements PersistentTokenRepository{

	@Autowired
	private RememberMeTokenRepository rememberMeTokenRepository;
	
	@Transactional()
	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		
		PersistentLogin persistentLogin=new PersistentLogin(token);
		rememberMeTokenRepository.save(persistentLogin);
	}

	@Transactional()
	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		
		PersistentLogin persistentLogin=rememberMeTokenRepository.findBySeries(series);
		if (persistentLogin!=null) {
			persistentLogin.setToken(tokenValue);
			persistentLogin.setDate(lastUsed);
			rememberMeTokenRepository.save(persistentLogin);
		}
		
	}

	@Transactional(readOnly = true)
	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		
		PersistentLogin token=rememberMeTokenRepository.findBySeries(seriesId);
		
		return new PersistentRememberMeToken(token.getUsername(), token.getSeries(), token.getToken(), token.getDate());
	}

	@Transactional()
	@Override
	public void removeUserTokens(String username) {
		
		List<PersistentLogin> persistentLogins=rememberMeTokenRepository.findByUsername(username);
		rememberMeTokenRepository.delete(persistentLogins);
	}

}
