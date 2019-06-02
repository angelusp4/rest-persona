package com.lapm.restpersona.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="persona")
public class PersonaEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer idPersona;
	
	private String nombre;
	
	private String sexo;
	
	private LocalDate fechaNacimiento;
	
	private String nacionalidad;
	
	private String telefono;
	
	private String email;
	
	public PersonaEntity(Integer idPersona, String nombre, String sexo, LocalDate fechaNacimiento, String nacionalidad, String telefono,
			String email) {
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.email = email;
	}

	public PersonaEntity() {
	}
	
	

}
