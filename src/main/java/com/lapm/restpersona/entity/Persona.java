package com.lapm.restpersona.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lapm.restpersona.dto.PersonaDTO;

import lombok.Data;

@Data
@Entity
@Table(name="persona")
public class Persona implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String sexo;
	private LocalDate fechaNacimiento;
	private String nacionalidad;
	private String telefono;
	private String email;
	
	public PersonaDTO toModel() {
		PersonaDTO persona = new PersonaDTO();
		persona.setId(id);
		persona.setNombre(nombre);
		persona.setSexo(sexo);
		persona.setFechaNacimiento(fechaNacimiento);
		persona.setNacionalidad(nacionalidad);
		persona.setTelefono(telefono);
		persona.setEmail(email);
		return persona;
	}
	
}
