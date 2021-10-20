package com.lapm.restpersona.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lapm.restpersona.dto.PersonaDTO;
import com.lapm.restpersona.entity.Persona;

public interface PersonaService {

	PersonaDTO consultarPersonaPorId(Integer id);

	Page<PersonaDTO> consultarPersonas(Pageable pageable);

	PersonaDTO guardarPersona(Persona persona);

	void actualizarPersona(Persona persona);

	void borrarPersonaPorId(Integer id);
}
