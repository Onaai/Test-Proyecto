package com.integrador.auth.dto;

import com.integrador.entity.Rol;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	
    private String email;
    private String name;
    private String lastname;
    private String password;
    
    private boolean adminCheck;
    private String adminCode;
    
    
}