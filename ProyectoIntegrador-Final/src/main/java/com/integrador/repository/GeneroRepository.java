package com.integrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integrador.entity.Genero;


public interface GeneroRepository extends JpaRepository<Genero, Long> {

	Genero findByGenero(String genero);

}
