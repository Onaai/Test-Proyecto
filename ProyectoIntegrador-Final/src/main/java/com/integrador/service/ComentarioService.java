package com.integrador.service;

import com.integrador.entity.Comentario;
import com.integrador.entity.Libro;
import com.integrador.entity.User;
import com.integrador.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public void agregarComentario(String texto, Libro libro, User user) {
        Comentario comentario = new Comentario();
        comentario.setTexto(texto);
        comentario.setLibro(libro);
        comentario.setUser(user);
        comentario.setFechaComentario(LocalDateTime.now());
        comentarioRepository.save(comentario);
    }

    public List<Comentario> getComentariosByLibro(Libro libro) {
        return comentarioRepository.findByLibro(libro);
    }
}