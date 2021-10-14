package com.lapm.restpersona.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lapm.restpersona.entity.PersonaEntity;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer>{

	public Page<PersonaEntity> findAll(Pageable pageable);
	
}
