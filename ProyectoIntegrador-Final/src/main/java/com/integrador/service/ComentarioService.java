package com.integrador.service;

import com.integrador.entity.Comentario;
import com.integrador.entity.ComentarioLike;
import com.integrador.entity.Libro;
import com.integrador.entity.User;
import com.integrador.repository.ComentarioLikeRepository;
import com.integrador.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ComentarioLikeRepository comentarioLikeRepository;

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

    // Método para aumentar los likes
    public void aumentarLikes(Integer comentarioId) {
        Comentario comentario = comentarioRepository.findById(comentarioId).orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
        comentario.setLikes(comentario.getLikes() + 1);
        comentarioRepository.save(comentario);
    }

    public boolean toggleLike(Integer comentarioId, User user) {
        Comentario comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));

        Optional<ComentarioLike> comentarioLikeOpt = comentarioLikeRepository
                .findByComentarioAndUser(comentario, user);

        if (comentarioLikeOpt.isPresent()) {
            ComentarioLike comentarioLike = comentarioLikeOpt.get();
            if (comentarioLike.isLiked()) {
                comentario.setLikes(comentario.getLikes() - 1);
                comentarioLike.setLiked(false);
                comentarioLikeRepository.save(comentarioLike);
                comentarioRepository.save(comentario);
                comentarioLikeRepository.delete(comentarioLike);
                System.out.println("Dislike: Fila eliminada para el comentario ID: " + comentarioId);
                return false;  // Like fue quitado

            } else {
                comentario.setLikes(comentario.getLikes() + 1);
                comentarioLike.setLiked(true);
            }
        } else {
            ComentarioLike comentarioLike = new ComentarioLike();
            comentarioLike.setComentario(comentario);
            comentarioLike.setUser(user);
            comentarioLike.setLiked(true);
            comentario.setLikes(comentario.getLikes() + 1);
            comentarioLikeRepository.save(comentarioLike);
        }
        comentarioRepository.save(comentario);
        return true;  // Agregó el like
    }

    public int getLikesCount(Integer comentarioId) {
        Comentario comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
        return comentario.getLikes();
    }

    public boolean usuarioHaDadoLike(Comentario comentario, User user) {
        return comentarioLikeRepository.existsByComentarioAndUser(comentario, user);
    }


}