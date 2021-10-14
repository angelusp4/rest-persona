package com.lapm.restpersona.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lapm.restpersona.exception.ResourceNotFoundException;
import com.lapm.restpersona.model.Persona;
import com.lapm.restpersona.service.PersonaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
	
@RestController
@RequestMapping("/personas")
@Api(value="Servicio REST de personas")
@Slf4j
public class PersonaController {

	@Autowired
	private PersonaService personaService;
	
	@ApiOperation("Regresa los datos de todas las personas registradas")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Persona> consultarPersonas(Pageable pageable){
		return personaService.consultarPersonas(pageable);
	}
	
	@ApiOperation("Regresa los datos de una persona por su id")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Persona consultarPersonaPorId(@PathVariable("id") Integer id){
		Persona persona = personaService.consultarPersonaPorId(id);
		if (persona == null)
			throw new ResourceNotFoundException("No se encontraron resultados para el id: " + id);
		return persona;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Guarda los datos de una persona")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Persona guardarPersona(@RequestBody @Valid Persona persona){
		return personaService.guardarPersona(persona.toEntity());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("Actualiza los datos de una persona")
	@PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public void actualizarPersona(@PathVariable("id") Integer id, @RequestBody @Valid Persona persona){
		log.info("Actualizando persona con id: {}", id);
		if (personaService.consultarPersonaPorId(id) == null)
			throw new ResourceNotFoundException("No se encontraron resultados para el id: " + id);
		persona.setId(id);
		personaService.actualizarPersona(persona.mergeEntity());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("borra los datos de una persona por su id")
	@DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public void borrarPersonaPorId(@PathVariable("id") Integer id){
		if (personaService.consultarPersonaPorId(id) == null)
			throw new ResourceNotFoundException("No se encontraron resultados para el id: " + id);
		personaService.borrarPersonaPorId(id);
	}
}
