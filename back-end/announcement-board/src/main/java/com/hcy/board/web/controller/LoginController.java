package com.hcy.board.web.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcy.board.service.auth.TabSessionManager;
import com.hcy.board.service.auth.model.LoginUser;
import com.hcy.board.service.user.UserService;
import com.hcy.board.service.util.ApiResponse;
import com.hcy.board.service.util.DebugTrace;

@Controller
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	private static final DebugTrace TRACE = new DebugTrace(LOGGER, LOGGER.isDebugEnabled());

	@Autowired
	private UserService userService;

	@Autowired
	private TabSessionManager tabSessionManager;

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<ApiResponse<?>> login(@RequestParam String username, @RequestParam String password) {

		LOGGER.info("Login attempt: username={}", username);
		LoginUser loginUser = userService.login(username, password);

		if (loginUser == null) {
			LOGGER.warn("Login failed: username={}", username);
			return ResponseEntity.ok(ApiResponse.fail("帳號或密碼錯誤"));
		}

		String tabToken = UUID.randomUUID().toString();
		tabSessionManager.put(tabToken, loginUser);

		LOGGER.info("Login success: username={}", username);
		Map<String, String> data = new HashMap<>();
		data.put("tabToken", tabToken);
		return ResponseEntity.ok(ApiResponse.ok(data));
	}

	@PostMapping("/logout")
	@ResponseBody
	public ResponseEntity<ApiResponse<?>> logout(@RequestParam String tabToken) {

		LOGGER.info("Logout: tabToken={}", tabToken);
		tabSessionManager.remove(tabToken);
		return ResponseEntity.ok(ApiResponse.ok());
	}

}
