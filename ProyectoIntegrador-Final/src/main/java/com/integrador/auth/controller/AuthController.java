package com.integrador.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integrador.auth.dto.AuthResponse;
import com.integrador.auth.dto.LoginRequest;
import com.integrador.auth.dto.RegisterRequest;
import com.integrador.auth.service.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@PostMapping(value = "/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request, HttpServletResponse response){

		try {
			AuthResponse token = authService.login(request);
			
			Cookie cookie = new Cookie("auth_token", token.getToken());
            cookie.setHttpOnly(true);
            
            cookie.setPath("/");
            cookie.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(cookie);
			
			
			return ResponseEntity.ok(token);
			
			
		} catch (AccessDeniedException ex) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
		System.out.println(request);
		try {
			AuthResponse response = authService.register(request);
			return ResponseEntity.ok(response);			
		} catch (AccessDeniedException ex){
			return ResponseEntity.internalServerError().build();
		}
		
		
	

	}
	
}
