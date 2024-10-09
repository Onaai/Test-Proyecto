package com.integrador.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.integrador.auth.dto.AuthResponse;
import com.integrador.auth.dto.LoginRequest;
import com.integrador.auth.dto.RegisterRequest;
import com.integrador.auth.jwt.JwtService;
import com.integrador.entity.Rol;
import com.integrador.entity.User;
import com.integrador.repository.RolRepository;
import com.integrador.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RolRepository rolRepository;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	public AuthResponse login(LoginRequest request) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(), 
						request.getPassword()
				));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		UserDetails user = userRepository.findByEmail(request.getEmail()).orElseThrow();
		
		String token = jwtService.getToken(user);
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setToken(token);
		
		return authResponse;
		
	}
	
	public AuthResponse register(RegisterRequest request) {
		
		Rol rol = rolRepository.findByName("ROLE_SOCIO").get();
		
		if(request.isAdminCheck() && request.getAdminCode().equals("admin")) {
			rol = rolRepository.findByName("ROLE_ADMIN").get();
		} 
		
		User user = new User();
		user.setEmail(request.getEmail());
		user.setName(request.getName());
		user.setLastname(request.getLastname());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setActivo(true);
		
		user.addRole(rol);
		
		userRepository.save(user);
		
		AuthResponse authResponse = new AuthResponse();
		
		authResponse.setToken(jwtService.getToken(user));
		
		return authResponse;
	}

}
