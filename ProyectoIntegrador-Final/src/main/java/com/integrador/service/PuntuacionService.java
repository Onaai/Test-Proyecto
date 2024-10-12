package com.integrador.service;

import com.integrador.entity.Libro;
import com.integrador.entity.Puntuacion;
import com.integrador.entity.User;
import com.integrador.repository.LibroRepository;
import com.integrador.repository.PuntuacionRepository;
import com.integrador.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PuntuacionService {

    @Autowired
    private PuntuacionRepository puntuacionRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private UserRepository userRepository;

    public void savePuntuacion(Long libroId, Integer userId, int puntuacionValue) {
        // Buscar el libro y el usuario por sus IDs
        Libro libro = libroRepository.findById(libroId)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Buscar si ya existe una puntuación para ese libro y usuario
        Optional<Puntuacion> existingPuntuacion = puntuacionRepository.findByLibroAndUser(libro, user);

        if (existingPuntuacion.isPresent()) {
            // Si ya existe, actualizamos la puntuación
            Puntuacion puntuacion = existingPuntuacion.get();
            puntuacion.setPuntuacion(puntuacionValue);
            puntuacionRepository.save(puntuacion);
        } else {
            // Si no existe, creamos una nueva puntuación
            Puntuacion nuevaPuntuacion = new Puntuacion();
            nuevaPuntuacion.setLibro(libro);
            nuevaPuntuacion.setUser(user);
            nuevaPuntuacion.setPuntuacion(puntuacionValue);
            puntuacionRepository.save(nuevaPuntuacion);
        }
    }

    // Obtener la puntuación de un libro por parte de un usuario
    public Optional<Puntuacion> getPuntuacionByLibroAndUser(Libro libro, User user) {
        return puntuacionRepository.findByLibroAndUser(libro, user);
    }
}
