package com.lapm.restpersona.test;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lapm.restpersona.dto.Persona;
import com.lapm.restpersona.entity.PersonaEntity;
import com.lapm.restpersona.repository.PersonaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PersonaTest {
	private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonaRepository personaRepository;
    
    @Autowired
	private ModelMapper modelMapper;

    @Before
    public void init() {
        PersonaEntity personaEntity = new PersonaEntity(1, "Alejandra Rodriguez Garcia", "F", LocalDate.of(1985, 12, 01), "Mexicana","5532123663","alejandra@gmail.com");
        when(personaRepository.findByIdPersona(1)).thenReturn(personaEntity);
    }

    @Test
    public void find_personaId_OK() throws Exception {

        mockMvc.perform(get("/persona/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPersona", is(1)))
                .andExpect(jsonPath("$.nombre", is("Alejandra Rodriguez Garcia")))
                .andExpect(jsonPath("$.sexo", is("F")))
                .andExpect(jsonPath("$.fechaNacimiento", is("1985-12-01")))
                .andExpect(jsonPath("$.nacionalidad", is("Mexicana")))
                .andExpect(jsonPath("$.telefono", is("5532123663")))
                .andExpect(jsonPath("$.email", is("alejandra@gmail.com")));

        verify(personaRepository, times(1)).findByIdPersona(1);

    }

    @Test
    public void find_allPersona_OK() throws Exception {

        List<PersonaEntity> personasEntity = Arrays.asList(
                new PersonaEntity(1, "Alejandra Rodriguez Garcia", "F", LocalDate.of(1985, 12, 01), "Mexicana","5532123663","alejandra@gmail.com"),
                new PersonaEntity(2, "Pedro Aguilar Lopez", "M", LocalDate.of(1980, 9, 01), "Colombiana","5514785236","pedro@gmail.com"),
                new PersonaEntity(3, "Maria del Rosario Dominguez Sanchez", "F", LocalDate.of(1989, 11, 9), "Mexicana","5536985245","rosario@gmail.com"),
                new PersonaEntity(4, "Mario Martinez Rodriguez", "M", LocalDate.of(1981, 01, 10), "Mexicana","5512365478","mario@gmail.com")
                );
        
        Page<PersonaEntity> personasPage = new PageImpl<>(personasEntity);

        when(personaRepository.findAll(any(Pageable.class))).thenReturn(personasPage);

        mockMvc.perform(get("/persona"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$", hasSize(4)))
//                .andExpect(jsonPath("$[0].idPersona", is(1)))
//                .andExpect(jsonPath("$[0].nombre", is("Alejandra Rodriguez Garcia")))
//                .andExpect(jsonPath("$[0].sexo", is("F")))
//                .andExpect(jsonPath("$[0].fechaNacimiento", is("1985-12-01")))
//                .andExpect(jsonPath("$[0].nacionalidad", is("Mexicana")))
//                .andExpect(jsonPath("$[0].telefono", is("5532123663")))
//                .andExpect(jsonPath("$[0].email", is("alejandra@gmail.com")))
//                .andExpect(jsonPath("$[1].idPersona", is(2)))
//                .andExpect(jsonPath("$[1].nombre", is("Pedro Aguilar Lopez")))
//                .andExpect(jsonPath("$[1].sexo", is("M")))
//                .andExpect(jsonPath("$[1].fechaNacimiento", is("1980-09-01")))
//                .andExpect(jsonPath("$[1].nacionalidad", is("Colombiana")))
//                .andExpect(jsonPath("$[1].telefono", is("5514785236")))
//                .andExpect(jsonPath("$[1].email", is("pedro@gmail.com")))
//                .andExpect(jsonPath("$[2].idPersona", is(3)))
//                .andExpect(jsonPath("$[2].nombre", is("Maria del Rosario Dominguez Sanchez")))
//                .andExpect(jsonPath("$[2].sexo", is("F")))
//                .andExpect(jsonPath("$[2].fechaNacimiento", is("1989-11-09")))
//                .andExpect(jsonPath("$[2].nacionalidad", is("Mexicana")))
//                .andExpect(jsonPath("$[2].telefono", is("5536985245")))
//                .andExpect(jsonPath("$[2].email", is("rosario@gmail.com")))
//                .andExpect(jsonPath("$[3].idPersona", is(4)))
//                .andExpect(jsonPath("$[3].nombre", is("Mario Martinez Rodriguez")))
//                .andExpect(jsonPath("$[3].sexo", is("M")))
//                .andExpect(jsonPath("$[3].fechaNacimiento", is("1981-01-10")))
//                .andExpect(jsonPath("$[3].nacionalidad", is("Mexicana")))
//                .andExpect(jsonPath("$[3].telefono", is("5512365478")))
//                .andExpect(jsonPath("$[3].email", is("mario@gmail.com")));

        verify(personaRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    public void find_personaIdNotFound_404() throws Exception {
        mockMvc.perform(get("/persona/5")).andExpect(status().isNotFound());
    }

    @Test
    public void save_persona_OK() throws Exception {

    	PersonaEntity personaEntity = new PersonaEntity(5, "Luis Perdomo", "M", LocalDate.of(1989, 06, 12), "Mexicana","5555555555","luis@gmail.com");
    	when(personaRepository.save(any(PersonaEntity.class))).thenReturn(personaEntity);
    	

        mockMvc.perform(post("/persona")
                .content(om.writeValueAsString(modelMapper.map(personaEntity, Persona.class)))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(personaRepository, times(1)).save(any(PersonaEntity.class));

    }
    

    @Test
    public void update_persona_OK() throws Exception {

    	PersonaEntity personaEntity = new PersonaEntity(1, "Alejandra Garcia", "F", LocalDate.of(1985, 12, 01), "Mexicana","5511111111","alejandra@gmail.com");
        when(personaRepository.save(any(PersonaEntity.class))).thenReturn(personaEntity);

        mockMvc.perform(put("/persona")
                .content(om.writeValueAsString(modelMapper.map(personaEntity, Persona.class)))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPersona", is(1)))
                .andExpect(jsonPath("$.nombre", is("Alejandra Garcia")))
                .andExpect(jsonPath("$.sexo", is("F")))
                .andExpect(jsonPath("$.fechaNacimiento", is("1985-12-01")))
                .andExpect(jsonPath("$.nacionalidad", is("Mexicana")))
                .andExpect(jsonPath("$.telefono", is("5511111111")))
                .andExpect(jsonPath("$.email", is("alejandra@gmail.com")));


    }

    @Test
    public void update_persona_404() throws Exception {

    	PersonaEntity personaEntity = new PersonaEntity(100, "Alejandra Garcia", "F", LocalDate.of(1985, 12, 01), "Mexicana","5511111111","alejandra@gmail.com");
       // when(personaRepository.save(any(PersonaEntity.class))).thenReturn(personaEntity);

        mockMvc.perform(put("/persona")
                .content(om.writeValueAsString(modelMapper.map(personaEntity, Persona.class)))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());


    }

    @Test
    public void delete_persona_OK() throws Exception {

        doNothing().when(personaRepository).deleteByIdPersona(1);

        mockMvc.perform(delete("/persona/1"))
                .andExpect(status().isOk());

        verify(personaRepository, times(1)).deleteByIdPersona(1);
    }
    
    @Test
    public void delete_persona_404() throws Exception {

       // doNothing().when(personaRepository).deleteByIdPersona(200);

        mockMvc.perform(delete("/persona/200"))
                .andExpect(status().isNotFound());

    }


}
