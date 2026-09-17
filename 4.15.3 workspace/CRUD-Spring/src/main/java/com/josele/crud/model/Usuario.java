package com.josele.crud.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Usuario {

	private Long id;
	private String nombre;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fechaDeCreacion;

	public Usuario() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaDeCreacion() {
		return fechaDeCreacion;
	}

	public void setFechaDeCreacion(Date fechaDeCreacion) {
		this.fechaDeCreacion = fechaDeCreacion;
	}

}