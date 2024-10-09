package com.integrador.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Libro {
	private Long id;
	private String titulo;
	private String img;
	private String url;
	private double precio;
	private List<String> generos;
	
}
