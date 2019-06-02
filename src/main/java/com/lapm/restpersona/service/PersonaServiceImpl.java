package com.lapm.restpersona.service;

import java.lang.reflect.Type;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.gson.reflect.TypeToken;
import com.lapm.restpersona.dto.Persona;
import com.lapm.restpersona.entity.PersonaEntity;
import com.lapm.restpersona.exception.BadParametersException;
import com.lapm.restpersona.exception.ModeloNotFoundException;
import com.lapm.restpersona.repository.PersonaRepository;


@Service
public class PersonaServiceImpl implements PersonaService{


	@Autowired
	private PersonaRepository personaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Persona consultarPersonaPorId(Integer idPersona) {
		PersonaEntity personaEntity = personaRepository.findByIdPersona(idPersona);
		if (personaEntity == null)
			throw new ModeloNotFoundException("No se encontraron resultados para el idPersona: " + idPersona);
		return modelMapper.map(personaEntity, Persona.class);
	}
	
	@Override
	public Page<Persona> consultarPersonas(Pageable pageable) {
		Page<PersonaEntity> entityList = personaRepository.findAll(pageable);
		if(entityList==null || entityList.getNumberOfElements()==0)
			throw new ModeloNotFoundException("No se encontraron resultados ");
		
		Type listType = new TypeToken<Page<Persona>>(){}.getType();
		
		return new ModelMapper().map(entityList, listType);
	}
	
	@Override
	@Transactional
	public void guardarPersona(Persona persona) {
		PersonaEntity personaEntity = personaRepository.findByIdPersona(persona.getIdPersona());
		if (personaEntity != null)
			throw new BadParametersException("El idPersona: " + persona.getIdPersona() + " ya existe");
		personaRepository.save(modelMapper.map(persona, PersonaEntity.class));
	}
	
	@Override
	@Transactional
	public void actualizarPersona(Persona persona) {
		PersonaEntity personaEntity = personaRepository.findByIdPersona(persona.getIdPersona());
		if (personaEntity == null)
			throw new ModeloNotFoundException("El idPersona: " + persona.getIdPersona() + " no existe");
		personaRepository.save(modelMapper.map(persona, PersonaEntity.class));
	}
	
	@Override
	@Transactional
	public void borrarPersonaPorId(Integer idPersona) {
		PersonaEntity personaEntity = personaRepository.findByIdPersona(idPersona);
		if (personaEntity == null)
			throw new ModeloNotFoundException("El idPersona: " + idPersona + " no existe");
		personaRepository.deleteByIdPersona(idPersona);
	}
}
