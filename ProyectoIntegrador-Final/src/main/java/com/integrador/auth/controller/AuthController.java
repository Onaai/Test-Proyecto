package com.integrador.auth.controller;

import com.integrador.entity.PasswordResetToken;
import com.integrador.entity.User;
import com.integrador.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.integrador.auth.service.EmailService;
import com.integrador.auth.dto.AuthResponse;
import com.integrador.auth.dto.LoginRequest;
import com.integrador.auth.dto.RegisterRequest;
import com.integrador.auth.service.AuthService;
import com.integrador.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private PasswordResetTokenRepository tokenRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private AuthService authService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping(value = "/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request, HttpServletResponse response) {

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
		} catch (AccessDeniedException ex) {
			return ResponseEntity.internalServerError().build();
		}

	}

	@PostMapping("/reset-password")
	public ResponseEntity<Map<String, String>> resetPassword(@RequestBody Map<String, String> request) {
		log.info("Recibiendo solicitud de restablecimiento de contraseña.");

		String token = request.get("token");
		String newPassword = request.get("newPassword");

		PasswordResetToken resetToken = tokenRepository.findByToken(token)
				.orElseThrow(() -> new IllegalArgumentException("Token inválido"));

		log.info("Token válido: {}", token);

		if (resetToken.getExpirationDate().isBefore(LocalDateTime.now())) {
			log.warn("El token ha expirado.");
			return ResponseEntity.status(400).body(Map.of("error", "El token ha expirado"));
		}

		User user = resetToken.getUser();
		log.info("Usuario asociado al token: {}", user.getEmail());

		// Encriptar la nueva contraseña
		String hashedPassword = passwordEncoder.encode(newPassword);
		user.setPassword(hashedPassword);

		userRepository.save(user);
		tokenRepository.delete(resetToken);
		log.info("Contraseña restablecida exitosamente para: {}", user.getEmail());

		return ResponseEntity.ok(Map.of("success", "true"));
	}

	@PostMapping("/forgot-password")
	public ResponseEntity<Map<String, String>> forgotPassword(@RequestBody Map<String, String> request) {
		String email = request.get("email");
		log.info("Solicitud de recuperación de contraseña para email: {}", email);

		try {
			// Buscar al usuario por email
			User user = userRepository.findByEmail(email)
					.orElseThrow(() -> new IllegalArgumentException("Correo no encontrado"));

			log.info("Usuario encontrado: {}", user.getEmail());

			// Generar token
			String token = UUID.randomUUID().toString();
			log.info("Token generado: {}", token);

			// Crear y guardar el token en la base de datos
			PasswordResetToken resetToken = new PasswordResetToken();
			resetToken.setToken(token);
			resetToken.setUser(user);
			resetToken.setExpirationDate(LocalDateTime.now().plusMinutes(30));

			tokenRepository.save(resetToken);
			log.info("Token guardado en la base de datos");

			// Crear URL de restablecimiento y enviar correo
			String resetUrl = "http://localhost:8080/reset-password.html?token=" + token;
			emailService.sendPasswordResetEmail(email, resetUrl);
			log.info("Correo de recuperación enviado a: {}", email);

			return ResponseEntity.ok(Map.of("success", "true"));

		} catch (IllegalArgumentException ex) {
			log.error("Correo no encontrado: {}", email, ex);
			return ResponseEntity.status(404).body(Map.of("success", "false", "message", "Correo no encontrado"));
		} catch (Exception ex) {
			log.error("Error inesperado durante la recuperación de contraseña", ex);
			return ResponseEntity.internalServerError().body(Map.of("success", "false", "message", "Error interno"));
		}
	}
}