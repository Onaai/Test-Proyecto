package com.integrador.repository;

import com.integrador.entity.Comentario;
import com.integrador.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    List<Comentario> findByLibro(Libro libro);
}
