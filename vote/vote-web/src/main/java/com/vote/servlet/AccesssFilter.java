package com.vote.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccesssFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest resq, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)resq;
		String clientId = CookieUtil.getCookie(request, "clientId");
		if(null==clientId) {
			HttpServletResponse response = (HttpServletResponse)resp;
			clientId = java.util.UUID.randomUUID().toString().replaceAll("-", "");
			CookieUtil.addCookie(response, "clientId", clientId, 60*60*24);
		}
		filterChain.doFilter(resq, resp);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
