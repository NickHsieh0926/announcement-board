package com.hcy.board.service.user;

import com.hcy.board.service.auth.model.LoginUser;

public interface UserService {
	
	LoginUser login(String username, String rawPassword);
	
}
