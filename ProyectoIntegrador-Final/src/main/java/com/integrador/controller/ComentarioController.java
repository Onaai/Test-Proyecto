package com.integrador.controller;

import com.integrador.entity.Comentario;
import com.integrador.entity.Libro;
import com.integrador.entity.User;
import com.integrador.service.ComentarioService;
import com.integrador.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pelicula")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private LibroService libroService;

    @PostMapping("/comentar")
    public String agregarComentario(@RequestParam Long libroId, @RequestParam String comentario, @AuthenticationPrincipal User user) {
        Libro libro = libroService.findPeliculaByID(libroId);
        comentarioService.agregarComentario(comentario, libro, user);
        return "redirect:/pelicula/detalle/" + libroId;
    }


}
