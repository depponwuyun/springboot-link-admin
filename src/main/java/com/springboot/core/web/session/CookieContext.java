package com.springboot.core.web.session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * client cookie tool class
 * 
 * @author Administrator
 *
 */
public class CookieContext {

	public static void set(String key, String value) {
		Cookie userCookie = new Cookie(key, value);
		userCookie.setPath("/");
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletResponse response = requestAttributes.getResponse();
		response.addCookie(userCookie);
	}

	public static void set(String key, String value, int expiry) {
		Cookie userCookie = new Cookie(key, value);

		userCookie.setMaxAge(30 * 24 * 60 * 60); // 存活期为一个月 30*24*60*60
		userCookie.setPath("/");
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletResponse response = requestAttributes.getResponse();
		response.addCookie(userCookie);
	}

	public static String get(String key) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(key)) {
					return cookie.getValue();
				}
			}
		}
		return "";
	}
}
