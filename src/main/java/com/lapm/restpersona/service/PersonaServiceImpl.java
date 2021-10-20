package com.lapm.restpersona.service;

import java.lang.reflect.Type;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.gson.reflect.TypeToken;
import com.lapm.restpersona.dto.PersonaDTO;
import com.lapm.restpersona.entity.Persona;
import com.lapm.restpersona.repository.PersonaRepository;


@Service
public class PersonaServiceImpl implements PersonaService{


	@Autowired
	private PersonaRepository personaRepository;
	
	@Override
	public PersonaDTO consultarPersonaPorId(Integer id) {
		Optional<Persona> personaEntity = personaRepository.findById(id);
		return personaEntity.isPresent() ? personaEntity.get().toModel() : null;
	}
	
	@Override
	public Page<PersonaDTO> consultarPersonas(Pageable pageable) {
		Page<Persona> entityList = personaRepository.findAll(pageable);
		Type listType = new TypeToken<Page<PersonaDTO>>(){}.getType();
		return new ModelMapper().map(entityList, listType);
	}
	
	@Override
	@Transactional
	public PersonaDTO guardarPersona(Persona persona) {
		personaRepository.save(persona);
		return persona.toModel();
	}
	
	@Override
	@Transactional
	public void actualizarPersona(Persona persona) {
		personaRepository.save(persona);
	}
	
	@Override
	@Transactional
	public void borrarPersonaPorId(Integer id) {
		personaRepository.deleteById(id);
	}
}
