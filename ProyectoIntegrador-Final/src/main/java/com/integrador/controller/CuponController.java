package com.integrador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integrador.entity.Cupon;
import com.integrador.requests.RequestCupon;
import com.integrador.service.CuponService;

@RestController
@RequestMapping("/cupon")
public class CuponController {

	@Autowired
	private CuponService cuponService;
	
	
	@PostMapping(value = "/get-cupon")
	public ResponseEntity<com.integrador.model.Cupon> getCuponByCodigo(@RequestBody RequestCupon request){
		
		Cupon cupon = cuponService.findCuponByCodigo(request.getCodigo());
		com.integrador.model.Cupon cuponModel = null;
		if (cupon!=null) {
			cuponModel = cupon.toDto();
		}
		
		return new ResponseEntity<>(cuponModel, HttpStatus.OK);
		
	}
	
	
}
