package com.lapm.restpersona.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lapm.restpersona.dto.Persona;
import com.lapm.restpersona.service.PersonaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
	
@RestController
@RequestMapping("/persona")
@Api(value="Servicio REST de personas")
public class PersonaController {

	@Autowired
	private PersonaService personaService;
	
	@ApiOperation("Regresa los datos de todas las personas registradas")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Persona>> consultarPersonas(Pageable pageable){
		return new ResponseEntity<>(personaService.consultarPersonas(pageable), HttpStatus.OK);
	}
	
	@ApiOperation("Regresa los datos de una persona por su id")
	@GetMapping(value = "/{idPersona}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Persona> consultarPersonaPorId(@PathVariable("idPersona") Integer idPersona){
		return new ResponseEntity<>(personaService.consultarPersonaPorId(idPersona), HttpStatus.OK);
	}
	
	@ApiOperation("Guarda los datos de una persona")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> guardarPersona(@RequestBody @Valid Persona persona){
		personaService.guardarPersona(persona);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@ApiOperation("Actualiza los datos de una persona")
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Persona> actualizarPersona(@RequestBody @Valid Persona persona){
		personaService.actualizarPersona(persona);
		return new ResponseEntity<>(persona, HttpStatus.OK);
	}
	
	@ApiOperation("borra los datos de una persona por su id")
	@DeleteMapping(value = "/{idPersona}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> borrarPersonaPorId(@PathVariable("idPersona") Integer idPersona){
		personaService.borrarPersonaPorId(idPersona);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
