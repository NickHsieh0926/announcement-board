package com.hcy.board.service.user.dao;

import com.hcy.board.service.user.entity.User;

public interface UserDao {

	User findByUsername(String username);

	void save(User user);

}
