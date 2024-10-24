package com.integrador.repository;

import java.util.List;

import com.integrador.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import com.integrador.entity.Libro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface LibroRepository extends JpaRepository<Libro, Long> {

	List<Libro> findByTituloContaining(String title);

	// Búsqueda por título o autor
	@Query("SELECT l FROM Libro l WHERE LOWER(l.titulo) LIKE LOWER(CONCAT('%', :busqueda, '%')) " +
			"OR LOWER(l.autor) LIKE LOWER(CONCAT('%', :busqueda, '%'))")
	List<Libro> findByTituloOrAutor(@Param("busqueda") String busqueda);

}
