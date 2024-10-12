package com.integrador.repository;

import com.integrador.entity.Libro;
import com.integrador.entity.Puntuacion;
import com.integrador.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PuntuacionRepository extends JpaRepository<Puntuacion, Long> {
    Optional<Puntuacion> findByLibroAndUser(Libro libro, User user);  // Método para encontrar la puntuación por libro y usuario
}

