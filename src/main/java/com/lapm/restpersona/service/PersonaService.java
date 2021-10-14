package com.lapm.restpersona.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lapm.restpersona.entity.PersonaEntity;
import com.lapm.restpersona.model.Persona;

public interface PersonaService {

	Persona consultarPersonaPorId(Integer id);

	Page<Persona> consultarPersonas(Pageable pageable);

	Persona guardarPersona(PersonaEntity persona);

	void actualizarPersona(PersonaEntity persona);

	void borrarPersonaPorId(Integer id);
}
