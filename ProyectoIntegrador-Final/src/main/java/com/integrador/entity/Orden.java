package com.integrador.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "ordenes")
public class Orden {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@CreatedDate
	private LocalDateTime fecha_creacion;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "socio_id")
	private User socio;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "estado_id")
	private EstadoOrden estadoOrden;
	
	@Column(name = "monto")
	private double monto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cupon_id")
	private Cupon cupon;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "order_peliculas", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "pelicula_id"))
	@JsonIgnore
	private Set<Libro> peliculas = new HashSet<>();
	
	@PrePersist
    protected void onCreate() {
        this.fecha_creacion = LocalDateTime.now();
    }
	
	public void addPelicula(Libro pelicula) {
		this.peliculas.add(pelicula);
	}

	public com.integrador.model.Orden toDto() {
		return new com.integrador.model.Orden(this.id, this.fecha_creacion, this.socio.getEmail(),this.estadoOrden.getEstado(), this.monto);		
	}

	
}
