package com.bandampla.condmyapp.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Optional;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieService {

	public static void setCookie(HttpServletResponse response, String key, String value, int seconds) {
		try {
			// create a cookie
			Cookie cookie = new Cookie(key, URLEncoder.encode(value,"UTF-8"));
			cookie.setMaxAge(seconds); // Set max age to 7 days
			cookie.setSecure(true);
			cookie.setHttpOnly(true);
			cookie.setPath("/");
			response.addCookie(cookie);
		} catch (IOException e) {
            e.printStackTrace();
		}
	}

    public static String getCookie(HttpServletRequest request, String key) {
        String value = null;
        try {
        	value = Optional.ofNullable(request.getCookies())
                    .flatMap(cookies -> Arrays.stream(cookies)
                            .filter(cookie -> key.equals(cookie.getName()))
                            .findAny()
                    )
                    .map(Cookie::getValue)
                    .orElse(null);

            if (value != null) {
            	value = URLDecoder.decode(value, "UTF-8");
            }

            return value;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
	}

    public static void deleteCookie(HttpServletResponse response, String key) {
        setCookie(response, key, "", 0);
    }
}
