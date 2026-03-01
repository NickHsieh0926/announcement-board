package com.hcy.board.web.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcy.board.service.exception.BusinessException;
import com.hcy.board.service.util.ApiResponse;
import com.hcy.board.service.util.DebugTrace;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	private static final DebugTrace TRACE = new DebugTrace(LOGGER, LOGGER.isDebugEnabled());

	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public Object handleBusinessException(BusinessException e, HttpServletRequest request) {
		LOGGER.warn("BusinessException: {}", e.getMessage());

		if (isAjaxRequest(request)) {
			return ResponseEntity.ok(ApiResponse.fail(e.getMessage()));
		}
		request.setAttribute("errorMessage", e.getMessage());
		return "error";
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Object handleException(Exception e, HttpServletRequest request) {
		LOGGER.error("Unexpected exception: {}", e.getMessage(), e);

		if (isAjaxRequest(request)) {
			return ResponseEntity.ok(ApiResponse.fail("系統發生錯誤，請稍後再試"));
		}
		request.setAttribute("errorMessage", "系統發生錯誤，請稍後再試");
		return "error";
	}

	private boolean isAjaxRequest(HttpServletRequest request) {
		String accept = request.getHeader("Accept");
		return accept != null && accept.contains("application/json");
	}

}
