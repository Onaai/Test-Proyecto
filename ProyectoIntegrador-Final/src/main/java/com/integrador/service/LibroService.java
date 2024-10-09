package com.integrador.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.entity.Genero;
import com.integrador.entity.Orden;
import com.integrador.entity.Libro;
import com.integrador.repository.GeneroRepository;
import com.integrador.repository.OrdenRepository;
import com.integrador.repository.LibroRepository;

@Service
public class LibroService {
	
	@Autowired
	private LibroRepository peliculaRepository;
	
	@Autowired
	private GeneroRepository generoRepository;
	
	@Autowired
	private OrdenRepository ordenRepository;
	
	public void savePelicula(com.integrador.model.Libro request) {
		Libro pelicula = new Libro();
		
		pelicula.setTitulo(request.getTitulo());
		pelicula.setImg(request.getImg());
		pelicula.setUrl(request.getUrl());
		pelicula.setPrecio(request.getPrecio());
		
		for(String g: request.getGeneros()) {
			Genero genero = generoRepository.findByGenero(g);
			genero.setGenero(g);
			pelicula.getGeneros().add(genero);	
		}
		peliculaRepository.save(pelicula);
	}
	
	public void updatePelicula(com.integrador.model.Libro request) {
		
		Optional<Libro> existePelicula = peliculaRepository.findById(request.getId());
		
		if(existePelicula.isPresent()) {
			Libro pelicula = existePelicula.get();
			
			pelicula.setTitulo(request.getTitulo());
			pelicula.setImg(request.getImg());
			pelicula.setUrl(request.getUrl());
			pelicula.setPrecio(request.getPrecio());
			
			Set<Genero> generos = new HashSet<>();
			
			for(String g: request.getGeneros()) {
				generos.add(generoRepository.findByGenero(g));
			}
			
			pelicula.setGeneros(generos);

			peliculaRepository.save(pelicula);
			
		}
		
		
	}
	
	public List<com.integrador.model.Libro> findAllPeliculas(){
		
		List<Libro> peliculasEntity = peliculaRepository.findAll();
		List<com.integrador.model.Libro> peliculasModel = new ArrayList<>();
		 
		for(Libro p: peliculasEntity) {
			peliculasModel.add(p.toDto());
		}
		
		
		return peliculasModel;
	}
	
	public Libro findPeliculaByID(Long id) {
		
		Optional<Libro> peliculaOpt = peliculaRepository.findById(id);
		
		if(peliculaOpt.isPresent()) {
			Libro pelicula = peliculaOpt.get();
			return pelicula;
		}
		
		return null;
		
	}

	public List<com.integrador.model.Libro> findPeliculasByString(String busqueda) {

		List<Libro> peliculasEntity = peliculaRepository.findByTituloContaining(busqueda);
		List<com.integrador.model.Libro> peliculasModel = new ArrayList<>();
		 
		for(Libro p: peliculasEntity) {
			peliculasModel.add(p.toDto());
		}
		
		
		return peliculasModel;
				
	}
	
	public void deletePeliculaById(Long id) {
		Libro pelicula = peliculaRepository.findById(id).orElse(null);
		
		if(pelicula != null) {
			List<Orden> ordenesConPelicula = ordenRepository.findByPeliculasContains(pelicula);
			
			if(!ordenesConPelicula.isEmpty()) {
				for(Orden o: ordenesConPelicula) {
					o.getPeliculas().clear();
				}	
			}
			pelicula.getGeneros().clear();
			peliculaRepository.deleteById(id);
		}
		
	}




	
}
