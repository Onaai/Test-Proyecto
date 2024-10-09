package com.integrador.auth.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String token = getTokenFromRequest(request);
		final String username;

		try {
			if (token == null ) {
				filterChain.doFilter(request, response);
				return;
			}
			
			jwtService.isTokenExpired(token);
			
		} catch (Exception ex) {
			filterChain.doFilter(request, response);
			return;
		}

		username = jwtService.getUsernameFromToken(token);

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			if (jwtService.isTokenValid(token, userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());

				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authToken);
			}

		}
		filterChain.doFilter(request, response);
	}

	private String getTokenFromRequest(HttpServletRequest request) {

		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}

		Cookie[] cookies = request.getCookies();

		if (request.getCookies() != null) {
			for (Cookie c : request.getCookies()) {
				if ("auth_token".equals(c.getName())) {
					return c.getValue();
				}
			}
		}
		return null;
	}
}
