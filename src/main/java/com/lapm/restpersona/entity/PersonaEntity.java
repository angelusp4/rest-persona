package com.lapm.restpersona.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name="persona")
public class PersonaEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull(message="El id de la persona es requerido")
	private Integer idPersona;
	
	@NotNull(message="El nombre es requerido")
	@Size(min=2, max=250, message="El nombre debe tener entre {min} y {max} caracteres")
	private String nombre;
	
	@NotNull(message="El sexo es requerido")
	@Pattern(regexp="M|F", message="El sexo solo puede ser M o F")
	@Size(min=1, max=1, message="El nombre debe tener entre {min} y {max} caracteres")
	private String sexo;
	
	@NotNull(message="Fecha de nacimiento es requerido")
	private LocalDate fechaNacimiento;
	
	@NotNull(message="Nacionalidad es requerido")
	@Size(min=2, max=100, message="El nombre debe tener entre {min} y {max} caracteres")
	private String nacionalidad;
	
	@NotNull(message="El telefono es requerido")
	@Size(min=10, max=10, message="El nombre debe tener entre {min} y {max} caracteres")
	private String telefono;
	
	@Size(min=5, max=300, message="El nombre debe tener entre {min} y {max} caracteres")
	@NotNull(message="El correo es requerido")
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
