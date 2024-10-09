package com.integrador.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.entity.Cupon;
import com.integrador.entity.Libro;
import com.integrador.repository.CuponRepository;

@Service
public class CuponService {
	
	@Autowired
	private CuponRepository cuponRepository;
	
	public Cupon findCuponByCodigo(String codigo) {
		
		Optional<Cupon> cuponOpt = cuponRepository.findByCodigo(codigo);
		
		if(cuponOpt.isPresent()) {
			Cupon cupon= cuponOpt.get();
			return cupon;
		}
		
		return null;
		
	}





	
}
