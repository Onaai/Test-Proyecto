package com.integrador.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Orden {
	private Long id;
	private LocalDateTime fechaCreacion;
	private String emailSocio;
	private String estado;
	private double monto;
	
}
