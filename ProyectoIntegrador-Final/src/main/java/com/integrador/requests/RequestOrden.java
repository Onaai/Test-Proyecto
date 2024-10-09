package com.integrador.requests;

import java.util.List;

import lombok.Getter;

@Getter
public class RequestOrden {
	private String email;
	private String cuponCodigo;
	private List<String> peliculas;
	}
