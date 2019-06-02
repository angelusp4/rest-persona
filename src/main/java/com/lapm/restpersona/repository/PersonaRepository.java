package com.lapm.restpersona.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lapm.restpersona.entity.PersonaEntity;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer>{
	public PersonaEntity findByIdPersona(Integer idPersona);
	public Page<PersonaEntity> findAll(Pageable pageable);
	//public void save(PersonaEntity personaEntity)
	public void deleteByIdPersona(Integer idPersona);
}
