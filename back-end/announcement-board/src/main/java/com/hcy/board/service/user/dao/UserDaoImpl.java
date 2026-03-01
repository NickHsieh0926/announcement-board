package com.hcy.board.service.user.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hcy.board.service.user.entity.User;
import com.hcy.board.service.util.DebugTrace;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class UserDaoImpl implements UserDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
	private static final DebugTrace TRACE = new DebugTrace(LOGGER, LOGGER.isDebugEnabled());

	@PersistenceContext
	private EntityManager em;

	@Override 
	public User findByUsername(String username) {
		LOGGER.debug("findByUsername: username={}", username);
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
		query.setParameter("username", username);
		User user = query.getResultStream().findFirst().orElse(null);
		LOGGER.debug("findByUsername result: {}", user != null ? "found" : "not found");
		return user;
	}

	@Override
	public void save(User user) {
		LOGGER.info("save user: username={}", user.getUsername());
		em.persist(user);
	}

}
