package com.integrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.integrador.entity.Watchlist;
import com.integrador.entity.Libro;
import com.integrador.entity.User;
import java.util.List;

public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {

    // Método para encontrar la lista de guardados por usuario
    List<Watchlist> findByUser(User user);

    // Método para verificar si un libro ya está en la lista de guardados de un usuario
    boolean existsByUserAndLibro(User user, Libro libro);

    // Método para eliminar un libro de la lista de guardados de un usuario
    void deleteByUserAndLibro(User user, Libro libro);
}
