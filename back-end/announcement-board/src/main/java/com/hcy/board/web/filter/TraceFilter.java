package com.hcy.board.web.filter;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.hcy.board.service.util.DebugTrace;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class TraceFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(TraceFilter.class);
	private static final DebugTrace TRACE = new DebugTrace(LOGGER, LOGGER.isDebugEnabled());

	private static final String TRACE_ID_KEY = "traceId";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String traceId = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
		MDC.put(TRACE_ID_KEY, traceId);

		try {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			LOGGER.debug("Request start: method={}, uri={}", httpRequest.getMethod(), httpRequest.getRequestURI());
			chain.doFilter(request, response);
		} finally {
			MDC.clear();
		}
	}
}
