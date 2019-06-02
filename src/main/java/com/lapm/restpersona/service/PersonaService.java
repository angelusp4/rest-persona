package com.lapm.restpersona.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lapm.restpersona.dto.Persona;

public interface PersonaService {

	Persona consultarPersonaPorId(Integer idPersona);

	Page<Persona> consultarPersonas(Pageable pageable);

	void guardarPersona(Persona persona);

	void actualizarPersona(Persona persona);

	void borrarPersonaPorId(Integer idPersona);
}
