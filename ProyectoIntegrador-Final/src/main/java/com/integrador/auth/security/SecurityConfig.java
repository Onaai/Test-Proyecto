package com.integrador.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.integrador.auth.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	@Autowired
    private AuthenticationProvider authProvider;
	
	public SecurityConfig() {
		super();
	}
    
 
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authRequest ->
                authRequest
                    .requestMatchers("/auth/**",
									"/reset-password.html",
		                    		"/login",
		                    		"/css/**",
		                    		"/js/**",
		                    		"/favicon.ico", 
		                    		"/",
		                    		"/home",
						"/pelicula/comentario/like/**",
								"/pelicula/comentario/like/{id}"
		                    		).permitAll()
                    .requestMatchers("/ordenes").hasAnyRole("ADMIN")
                    .requestMatchers("/mis-ordenes").hasAnyRole("SOCIO")
                    .requestMatchers("/orden/add").hasRole("SOCIO")
                    .requestMatchers("/orden/get-ordenes").hasAnyRole("SOCIO", "ADMIN")
                    .anyRequest().authenticated()
            )
            .sessionManagement(sessionManager ->
                sessionManager
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling(exceptionHandler ->
                exceptionHandler
                    .accessDeniedHandler(accessDeniedHandler())
            )
            .build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.sendRedirect("/error/403");
        };
    }
	
	
}