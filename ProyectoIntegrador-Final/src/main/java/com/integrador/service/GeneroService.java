package com.integrador.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.entity.Genero;
import com.integrador.entity.Libro;
import com.integrador.repository.GeneroRepository;

@Service
public class GeneroService {
	
	@Autowired
	private GeneroRepository generoRepository;
	
	public List<String> findAllGeneros(){
		
		List<Genero> generosEntity = generoRepository.findAll();
		List<String> generosString = new ArrayList<>();
		 
		for(Genero g: generosEntity) {
			generosString.add(g.getGenero());
		}
		
		
		return generosString;
	}

}
