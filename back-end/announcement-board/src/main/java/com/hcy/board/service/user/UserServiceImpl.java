package com.hcy.board.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcy.board.service.auth.model.LoginUser;
import com.hcy.board.service.user.dao.UserDao;
import com.hcy.board.service.user.entity.User;
import com.hcy.board.service.util.DebugTrace;
import com.hcy.board.service.util.PasswordUtil;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	private static final DebugTrace TRACE = new DebugTrace(LOGGER, LOGGER.isDebugEnabled());

    @Autowired
    private UserDao userDao;

    @Override
    public LoginUser login(String username, String rawPassword) {
        LOGGER.debug("login: username={}", username);
        User user = userDao.findByUsername(username);

        if (user == null) {
            LOGGER.warn("login: user not found, username={}", username);
            return null;
        }

        if (!PasswordUtil.verify(rawPassword, user.getPasswordHash())) {
            LOGGER.warn("login: password incorrect, username={}", username);
            return null;
        }

        LOGGER.info("login: success, username={}", username);
        return new LoginUser(user.getId(), user.getUsername(), user.getDisplayName());
    }
    
}
