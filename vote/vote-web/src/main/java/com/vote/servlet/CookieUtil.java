package com.vote.servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	
	private static String cookieDomain;
	
	public static void setCookieDomain(String cookieDomain) {
		CookieUtil.cookieDomain = cookieDomain;
	}

	public static void addCookie(HttpServletResponse response,String key, String val, int timeOut) {
		try {
			Cookie cookie = new Cookie(key,val);
			cookie.setMaxAge(timeOut);
			cookie.setPath("/");
			if(null!=cookieDomain && !"".equals(cookieDomain))
				cookie.setDomain(cookieDomain);
			response.addCookie(cookie);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static String getCookie(HttpServletRequest request,String key) {
		try {
			Cookie[] cookies = request.getCookies();
			if(null==cookies) {
				return null;
			}
			for(Cookie cookie : cookies) {
			    if(cookie.getName().equals(key)) {
			    	return cookie.getValue();
			    }
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static void removeCookie(HttpServletRequest request,HttpServletResponse response, String key) {
		try {
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie : cookies) {
			    if(cookie.getName().equals(key)) {
			    	cookie.setValue(null);
	                cookie.setMaxAge(0);
	                if(null!=cookieDomain &&!"".equals(cookieDomain))
	                	cookie.setDomain(cookieDomain);
	                cookie.setPath("/");
	                response.addCookie(cookie);
			    }
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
