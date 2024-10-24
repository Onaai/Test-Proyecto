package com.integrador.controller;

import java.util.*;

import com.integrador.entity.Comentario;
import com.integrador.entity.Libro;
import com.integrador.entity.Puntuacion;
import com.integrador.entity.User;
import com.integrador.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

	@Autowired
	private UserService userService;

	@GetMapping(value = "/home")
	public String misOrdenes(Model model, @AuthenticationPrincipal User user) {
		List<com.integrador.model.Libro> libros = libroService.findAllPeliculas();

		model.addAttribute("peliculas", libros);
		model.addAttribute("generos", generoService.findAllGeneros());

		boolean hasSeenNovedades = userService.hasSeenNovedades(user);
		model.addAttribute("hasSeenNovedades", hasSeenNovedades);

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


	@GetMapping("/pelicula/detalle/{id}")
	public String mostrarDetallePelicula(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal User user) {
		// Obtener el libro por ID
		Libro pelicula = libroService.findPeliculaByID(id);

		if (pelicula != null) {
			// Obtener los comentarios del libro
			List<Comentario> comentarios = comentarioService.getComentariosByLibro(pelicula);

			if (comentarios == null) {
				comentarios = new ArrayList<>();
			}

			// Crear un Map para almacenar si el usuario ha dado like a cada comentario
			Map<Long, Boolean> likesPorComentario = new HashMap<>();

			for (Comentario comentario : comentarios) {
				boolean usuarioHaDadoLike = comentarioService.usuarioHaDadoLike(comentario, user);
				likesPorComentario.put(comentario.getId(), usuarioHaDadoLike);
			}

			// Añadir los comentarios, el libro y los likes al modelo
			model.addAttribute("pelicula", pelicula);
			model.addAttribute("comentarios", comentarios);
			model.addAttribute("likesPorComentario", likesPorComentario);

			// Recuperar la puntuación del usuario si existe
			Optional<Puntuacion> puntuacionOpt = puntuacionService.getPuntuacionByLibroAndUser(pelicula, user);
			int puntuacion = puntuacionOpt.map(Puntuacion::getPuntuacion).orElse(0);  // Si no existe, será 0
			model.addAttribute("puntuacion", puntuacion);

			return "detallePelicula";
		} else {
			// Si no se encuentra el libro, redirigir a la página de inicio
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
