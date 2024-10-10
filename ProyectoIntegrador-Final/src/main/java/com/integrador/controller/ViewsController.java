package com.integrador.controller;

import java.util.List;

import com.integrador.entity.Libro;
import com.integrador.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.integrador.service.GeneroService;
import com.integrador.service.LibroService;
import com.integrador.service.WatchlistService;

@Controller
public class ViewsController {

	@Autowired
	private WatchlistService watchlistService;

	@Autowired
	private LibroService peliculaService;
	
	@Autowired
	private GeneroService generoService;
	
	@GetMapping(value = "/home")
	public String misOrdenes(Model model) {
		
		List<com.integrador.model.Libro> peliculas = peliculaService.findAllPeliculas();
		
		
		model.addAttribute("peliculas", peliculas);
		model.addAttribute("generos", generoService.findAllGeneros());
		
		return "home";
	}


	@PostMapping(value = "/busqueda")
	public String busquedaPeliculas(@RequestParam String busqueda, Model model) {
		
		List<com.integrador.model.Libro> peliculas = peliculaService.findPeliculasByString(busqueda);
		
		model.addAttribute("busqueda", busqueda);
		model.addAttribute("peliculas", peliculas);
		model.addAttribute("generos", generoService.findAllGeneros());
		
		return "busqueda";
	}
	@GetMapping(value = "/mis-ordenes")
	public String ordenesSocio() {

		return "ordenesSocio";
	}
	
	@GetMapping(value = "/ordenes")
	public String ordenesAdmin() {
		
		return "ordenesAdmin";
	}
	@GetMapping("/pelicula/detalle/{id}")
	public String mostrarDetallePelicula(@PathVariable("id") Long id, Model model) {
		// Obtener la película por ID
		Libro pelicula = peliculaService.findPeliculaByID(id);

		if (pelicula != null) {
			// Pasar la información de la película a la vista
			model.addAttribute("pelicula", pelicula);
		} else {
			// Si la película no se encuentra, redirigir a la página de inicio
			return "redirect:/home";
		}

		// Redirigir a la página de detalles de la película
		return "detallePelicula";
	}

	@PostMapping("/pelicula/guardar/{id}")
	public String addLibroToWatchlist(@PathVariable("id") Long libroId, @AuthenticationPrincipal User user) {
		try {
			// Ahora usamos la instancia inyectada 'watchlistService'
			watchlistService.addLibroToWatchlist(libroId, user);  // Correcto
		} catch (Exception e) {
			// Manejar cualquier error que ocurra, como que el libro ya esté guardado
			System.out.println(e.getMessage());
		}
		// Redirige al detalle del libro
		return "redirect:/pelicula/detalle/" + libroId;
	}


}



