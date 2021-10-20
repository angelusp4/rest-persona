package com.lapm.restpersona.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lapm.restpersona.entity.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Integer>{

	public Page<Persona> findAll(Pageable pageable);
	
}
