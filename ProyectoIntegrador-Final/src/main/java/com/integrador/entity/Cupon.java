package com.integrador.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.ToString;

@Entity
@ToString
@Getter
public class Cupon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "codigo",unique = true,  nullable = false, length = 255)
	private String codigo;
	
	@Column(name = "porcentaje", nullable = false)
	@Min(0)
    @Max(1)
    private Double porcentaje;
	
	public com.integrador.model.Cupon toDto(){
		return new com.integrador.model.Cupon(this.id, this.porcentaje);
	}
}
