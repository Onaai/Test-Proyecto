package com.integrador.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.integrador.model.Orden;
import com.integrador.requests.RequestOrden;
import com.integrador.requests.RequestOrdenesSocio;
import com.integrador.service.OrdenService;

@RestController
@RequestMapping("/orden")
public class OrdenController {

	@Autowired
	private OrdenService ordenService;
	
	@PostMapping(value = "/add")
	public ResponseEntity<Void> addOrden(@RequestBody RequestOrden request){
		
		ordenService.saveOrden(request);
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
	
	@PostMapping(value = "/get-ordenes")
	public ResponseEntity<List<Orden>> getOrdenes(@RequestBody(required= false) RequestOrdenesSocio request){
		List<Orden> ordenes;
		try {
			if(request != null) {
				ordenes = ordenService.findOrdenesByEmail(request.getEmail());
				return new ResponseEntity<>(ordenes, HttpStatus.OK);
			} else {

				ordenes = ordenService.findAllOrdenes();
				return new ResponseEntity<>(ordenes, HttpStatus.OK);
			}
		} catch (NoSuchElementException exception) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping(value = "/aprobar-orden")
	public ResponseEntity<Void> aprobarOrden(@RequestParam int id){
		
		ordenService.approveOrden(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/desaprobar-orden")
	public ResponseEntity<Void> desaprobarOrden(@RequestParam int id){
		
		ordenService.declineOrden(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
