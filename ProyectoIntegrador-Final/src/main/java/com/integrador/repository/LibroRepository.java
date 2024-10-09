package com.integrador.repository;

import java.util.List;

import com.integrador.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import com.integrador.entity.Libro;


public interface LibroRepository extends JpaRepository<Libro, Long> {

	List<Libro> findByTituloContaining(String title);

}
