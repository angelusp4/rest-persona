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
import com.lapm.restpersona.entity.PersonaEntity;
import com.lapm.restpersona.model.Persona;
import com.lapm.restpersona.repository.PersonaRepository;


@Service
public class PersonaServiceImpl implements PersonaService{


	@Autowired
	private PersonaRepository personaRepository;
	
	@Override
	public Persona consultarPersonaPorId(Integer id) {
		Optional<PersonaEntity> personaEntity = personaRepository.findById(id);
		return personaEntity.isPresent() ? personaEntity.get().toModel() : null;
	}
	
	@Override
	public Page<Persona> consultarPersonas(Pageable pageable) {
		Page<PersonaEntity> entityList = personaRepository.findAll(pageable);
		Type listType = new TypeToken<Page<Persona>>(){}.getType();
		return new ModelMapper().map(entityList, listType);
	}
	
	@Override
	@Transactional
	public Persona guardarPersona(PersonaEntity persona) {
		personaRepository.save(persona);
		return persona.toModel();
	}
	
	@Override
	@Transactional
	public void actualizarPersona(PersonaEntity persona) {
		personaRepository.save(persona);
	}
	
	@Override
	@Transactional
	public void borrarPersonaPorId(Integer id) {
		personaRepository.deleteById(id);
	}
}
