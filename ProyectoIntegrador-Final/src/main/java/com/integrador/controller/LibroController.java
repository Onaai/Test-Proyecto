package com.integrador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integrador.model.Libro;
import com.integrador.requests.RequestLibroId;
import com.integrador.service.LibroService;

@RestController
@RequestMapping("/pelicula")
public class LibroController {

	@Autowired
	private LibroService peliculaService;
	
	@PostMapping(value = "/add")
	public ResponseEntity<Void> addPelicula(@RequestBody Libro request){
		
		
		peliculaService.savePelicula(request);
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
	
	@PostMapping(value = "/modify")
	public ResponseEntity<Void> modifyPelicula(@RequestBody Libro request){
		
	
		peliculaService.updatePelicula(request);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/get-pelicula")
	public ResponseEntity<Libro> getPeliculaByID(@RequestBody RequestLibroId request){
		
		com.integrador.entity.Libro pelicula = peliculaService.findPeliculaByID(request.getId());
		
		Libro peliculaModel = pelicula.toDto();
	
		
		return new ResponseEntity<>(peliculaModel, HttpStatus.OK);
		
	}
	
	
	@DeleteMapping(value = "/delete")
	public ResponseEntity<Void> deletePelicula(@RequestBody RequestLibroId request){
			
		peliculaService.deletePeliculaById(request.getId());
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	
}
