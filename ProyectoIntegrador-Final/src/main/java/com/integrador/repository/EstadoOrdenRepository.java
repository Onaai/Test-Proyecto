package com.integrador.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integrador.entity.EstadoOrden;

public interface EstadoOrdenRepository extends JpaRepository<EstadoOrden, Integer>{
	
	EstadoOrden findByEstado(String estado);
}

