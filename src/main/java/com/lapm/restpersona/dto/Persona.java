package com.lapm.restpersona.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "muestra la informacion de una persona")
public class Persona {

	public Persona(Integer idPersona, String nombre, String sexo, LocalDate fechaNacimiento, String nacionalidad,
			String telefono, String email) {
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.email = email;
	}

	public Persona() {
	}

	@ApiModelProperty(notes = "Identificador unico de una persona que ya se registro.")
	@NotEmpty
	private Integer idPersona;

	@ApiModelProperty(notes = "Nombre completo.")
	@NotEmpty
	private String nombre;

	@ApiModelProperty(notes = "Genero, ingresar un caracter (M - Masculino / F - Femenino.")
	@NotEmpty
	private String sexo;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@ApiModelProperty(notes = "Fecha de nacimiento en formato YYYY-MM-DD.")
	@NotEmpty
	private LocalDate fechaNacimiento;

	@ApiModelProperty(notes = "Nacionalidad.")
	@NotEmpty
	private String nacionalidad;

	@ApiModelProperty(notes = "Telefono a 10 digitos.")
	@NotEmpty
	private String telefono;

	@ApiModelProperty(notes = "Correo electronico.")
	@NotEmpty
	private String email;
}
