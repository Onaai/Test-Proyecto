package com.integrador.service;

import com.integrador.entity.Libro;
import com.integrador.entity.User;
import com.integrador.entity.Watchlist;
import com.integrador.repository.LibroRepository;
import com.integrador.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistService {


    @Autowired
    private WatchlistRepository watchlistRepository;

    @Autowired
    private LibroRepository libroRepository;

    public void addLibroToWatchlist(Long libroId, User user) {
        // Buscar el libro por ID
        Libro libro = libroRepository.findById(libroId).orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        // Verificar si ya está en la lista de guardados
        if (watchlistRepository.existsByUserAndLibro(user, libro)) {
            throw new RuntimeException("El libro ya está en tu lista de guardados");
        }

        // Crear una nueva entrada en la watchlist
        Watchlist watchlist = new Watchlist();
        watchlist.setUser(user);
        watchlist.setLibro(libro);

        // Guardar en la base de datos
        watchlistRepository.save(watchlist);
    }

    public List<Watchlist> getWatchlistByUser(User user) {
        return watchlistRepository.findByUser(user);
    }

    public void removeLibroFromWatchlist(Long libroId, User user) {
        // Buscar el libro por ID
        Libro libro = libroRepository.findById(libroId).orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        // Eliminar el libro de la watchlist del usuario
        watchlistRepository.deleteByUserAndLibro(user, libro);
    }
}
