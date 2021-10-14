package com.lapm.restpersona.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.lapm.restpersona.entity.PersonaEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "muestra la informacion de una persona")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Persona implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -3721761266007973550L;

	@ApiModelProperty(notes = "Identificador unico de una persona que ya se registro.")
	private Integer id;

	@ApiModelProperty(notes = "Nombre completo.")
	@NotNull(message="El nombre es requerido")
	@Size(min=2, max=250, message="El nombre debe tener entre {min} y {max} caracteres")
	private String nombre;

	@ApiModelProperty(notes = "Genero, ingresar un caracter (M - Masculino / F - Femenino.")
	@NotNull(message="El sexo es requerido")
	@Pattern(regexp="M|F", message="El sexo solo puede ser M o F")
	@Size(min=1, max=1, message="El nombre debe tener entre {min} y {max} caracteres")
	private String sexo;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@ApiModelProperty(notes = "Fecha de nacimiento en formato YYYY-MM-DD.")
	@NotNull(message="Fecha de nacimiento es requerida")
	private LocalDate fechaNacimiento;

	@ApiModelProperty(notes = "Nacionalidad.")
	@NotNull(message="Nacionalidad es requerido")
	@Size(min=2, max=100, message="El nombre debe tener entre {min} y {max} caracteres")
	private String nacionalidad;

	@ApiModelProperty(notes = "Telefono a 10 digitos.")
	@NotNull(message="El telefono es requerido")
	@Size(min=10, max=10, message="El nombre debe tener entre {min} y {max} caracteres")
	private String telefono;

	@ApiModelProperty(notes = "Correo electronico.")
	@Size(min=5, max=300, message="El nombre debe tener entre {min} y {max} caracteres")
	@NotNull(message="El correo es requerido")
	private String email;
	
	public PersonaEntity toEntity(){
		PersonaEntity entity = new PersonaEntity();
		entity.setNombre(nombre);
		entity.setSexo(sexo);
		entity.setFechaNacimiento(fechaNacimiento);
		entity.setNacionalidad(nacionalidad);
		entity.setTelefono(telefono);
		entity.setEmail(email);
		return entity;
	}
	
	public PersonaEntity mergeEntity() {
		PersonaEntity entity = toEntity();
		entity.setId(id);
		return entity;
	}
}
