package com.hospitalms.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hospitalms.model.PersistentLogin;
import com.hospitalms.repository.PersistentLoginRespository;

@Repository("tokenRepositoryDao")
public class HibernateTokenRepository implements PersistentTokenRepository{

	private static final Logger LOG = LoggerFactory.getLogger(HibernateTokenRepository.class);

    @Autowired
    PersistentLoginRespository persistentLoginRepository;
    
    @Override
    public void createNewToken(PersistentRememberMeToken token) {
    	LOG.info("Creating Token for user : {}", token.getUsername());
        PersistentLogin persistentLogin = new PersistentLogin();
        persistentLogin.setUsername(token.getUsername());
        persistentLogin.setSeries(token.getSeries());
        persistentLogin.setToken(token.getTokenValue());
        persistentLogin.setLastUsed(token.getDate());
        persistentLoginRepository.save(persistentLogin);
    }
 
    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
     try {
    	 LOG.info("Fetch Token if any for seriesId : {}", seriesId);
        PersistentLogin persistentLogin = persistentLoginRepository.findById(seriesId).get();
        return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
        										persistentLogin.getToken(), persistentLogin.getLastUsed());
	    } catch (Exception e) {
	    	LOG.info("Token not found...");
	        return null;
	    }
    }
 
    @Override
    public void removeUserTokens(String username) {
    	LOG.info("Removing Token if any for user : {}", username);
        PersistentLogin persistentLogin = persistentLoginRepository.findByUsername(username);
        if (persistentLogin != null) {
        	LOG.info("rememberMe was selected");
            persistentLoginRepository.delete(persistentLogin);
        }
 
    }
 
    @Override
    public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
    	LOG.info("Updating Token for seriesId : {}", seriesId);
        PersistentLogin persistentLogin = persistentLoginRepository.findById(seriesId).get();
        persistentLogin.setToken(tokenValue);
        persistentLogin.setLastUsed(lastUsed);
        persistentLoginRepository.save(persistentLogin);
    }
}
