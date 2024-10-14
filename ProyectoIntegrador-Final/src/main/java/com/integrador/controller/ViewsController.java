package com.integrador.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.integrador.entity.Comentario;
import com.integrador.entity.Libro;
import com.integrador.entity.Puntuacion;
import com.integrador.entity.User;
import com.integrador.service.ComentarioService;
import com.integrador.service.PuntuacionService;  // Importa el servicio de puntuación
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.integrador.service.GeneroService;
import com.integrador.service.LibroService;

@Controller
public class ViewsController {

	@Autowired
	private LibroService libroService;

	@Autowired
	private GeneroService generoService;

	@Autowired
	private ComentarioService comentarioService;

	@Autowired
	private PuntuacionService puntuacionService;  // Inyecta el servicio de puntuación

	@GetMapping(value = "/home")
	public String misOrdenes(Model model) {
		List<com.integrador.model.Libro> libros = libroService.findAllPeliculas();

		model.addAttribute("peliculas", libros);
		model.addAttribute("generos", generoService.findAllGeneros());

		return "home";
	}

	@PostMapping(value = "/busqueda")
	public String busquedaLibros(@RequestParam String busqueda, Model model) {
		List<com.integrador.model.Libro> libros = libroService.findPeliculasByString(busqueda);

		model.addAttribute("busqueda", busqueda);
		model.addAttribute("peliculas", libros);
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

	// Mostrar detalles de la película (libro)
	@GetMapping("/pelicula/detalle/{id}")
	public String mostrarDetallePelicula(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal User user) {
		// Obtener la película por ID
		Libro pelicula = libroService.findPeliculaByID(id);

		if (pelicula != null) {
			// Pasar la información de la película y los comentarios a la vista
			List<Comentario> comentarios = comentarioService.getComentariosByLibro(pelicula);

			if (comentarios == null) {
				comentarios = new ArrayList<>();
			}

			// Añadir los comentarios y la película al modelo
			model.addAttribute("pelicula", pelicula);
			model.addAttribute("comentarios", comentarios);

			// Recuperar la puntuación del usuario si existe
			Optional<Puntuacion> puntuacionOpt = puntuacionService.getPuntuacionByLibroAndUser(pelicula, user);
			int puntuacion = puntuacionOpt.map(Puntuacion::getPuntuacion).orElse(0);  // Si no existe, será 0
			model.addAttribute("puntuacion", puntuacion);

			return "detallePelicula";
		} else {
			// Si la película no se encuentra, redirigir a la página de inicio
			return "redirect:/home";
		}
	}

	// Guardar la puntuación
	@PostMapping("/pelicula/puntuar")
	public String puntuar(@RequestParam("peliculaId") Long peliculaId,
						  @RequestParam("puntuacion") int puntuacion,
						  @AuthenticationPrincipal User user) {
		// Llama a savePuntuacion en vez de guardarPuntuacion
		puntuacionService.savePuntuacion(peliculaId, user.getId(), puntuacion);
		return "redirect:/pelicula/detalle/" + peliculaId;
	}

	@GetMapping("/reset-password.html")
	public String resetPasswordView() {
		return "reset-password"; // Nombre del archivo HTML en la carpeta templates sin extensión.
	}

}
