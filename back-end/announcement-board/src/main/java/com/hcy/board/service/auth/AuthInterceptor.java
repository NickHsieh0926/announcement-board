package com.hcy.board.service.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.hcy.board.service.auth.model.LoginUser;
import com.hcy.board.service.util.DebugTrace;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthInterceptor.class);
	private static final DebugTrace TRACE = new DebugTrace(LOGGER, LOGGER.isDebugEnabled());

	@Autowired
	private TabSessionManager tabSessionManager;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String tabToken = request.getParameter("tabToken");
		TRACE.message("preHandle: uri={}, tabToken={}", request.getRequestURI(), tabToken);

		LoginUser loginUser = tabSessionManager.get(tabToken);

		if (loginUser == null) {
			LOGGER.warn("Auth failed: invalid tabToken={}, uri={}, redirect to /login", tabToken,
					request.getRequestURI());
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}

		LOGGER.debug("Auth success: user={}, uri={}", loginUser.getUsername(), request.getRequestURI());
		request.setAttribute("currentUser", loginUser);
		request.setAttribute("ctx", request.getContextPath());
		return true;
	}

}
