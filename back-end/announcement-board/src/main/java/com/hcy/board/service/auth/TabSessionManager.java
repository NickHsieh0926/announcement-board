package com.hcy.board.service.auth;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.hcy.board.service.auth.model.LoginUser;

@Component
public class TabSessionManager {

	private final ConcurrentHashMap<String, LoginUser> sessionMap = new ConcurrentHashMap<>();

	public void put(String tabToken, LoginUser user) {
		sessionMap.put(tabToken, user);
	}

	public LoginUser get(String tabToken) {
		if (tabToken == null)
			return null;
		return sessionMap.get(tabToken);
	}

	public void remove(String tabToken) {
		if (tabToken != null) {
			sessionMap.remove(tabToken);
		}
	}

}
