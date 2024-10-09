package com.integrador.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integrador.entity.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer>{
	Optional<Rol> findByName(String name);
}
