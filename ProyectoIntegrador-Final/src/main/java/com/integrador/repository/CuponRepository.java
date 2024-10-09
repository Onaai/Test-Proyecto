package com.integrador.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integrador.entity.Cupon;

public interface CuponRepository extends JpaRepository<Cupon, Integer>{
	
	Optional<Cupon> findByCodigo(String codigo);
}

