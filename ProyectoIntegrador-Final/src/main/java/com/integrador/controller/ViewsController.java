package com.integrador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.integrador.service.GeneroService;
import com.integrador.service.LibroService;

@Controller
public class ViewsController {
	
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

	
}
