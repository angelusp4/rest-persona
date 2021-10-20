package com.lapm.restpersona.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lapm.restpersona.dto.PersonaDTO;
import com.lapm.restpersona.dto.ResponseDTO;
import com.lapm.restpersona.dto.exception.ResponseError;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@SqlGroup(value = {
		@Sql(value = "personas.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
		@Sql(value = "clear.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD) 
})
public class PersonaTest {
	private static final String PATH = "http://localhost:%s/personas/%s";
	private final String JSON_PATH = "classpath:/com/lapm/restpersona/controller/persona/";
	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@LocalServerPort
	private int port;
	
	@Test
	public void list() throws IOException {
		final String url = String.format(PATH, port, "");
		ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
		ResponseDTO<List<PersonaDTO>> restResponse = MAPPER.readValue(response.getBody(), ResponseDTO.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(restResponse.getContent().size()).isEqualTo(2);
	}
	
	@Test
	public void get() throws IOException {
		final String url = String.format(PATH, port, 2);
		ResponseEntity<PersonaDTO> response = restTemplate.getForEntity(url,PersonaDTO.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getNombre()).isEqualTo("Pedro Aguilar Lopez");
		assertThat(response.getBody().getSexo()).isEqualTo("M");
		assertThat(response.getBody().getEmail()).isEqualTo("pedro@mail.com");
	}
	
	@Test
	public void getNotFound() throws IOException {
		final String url = String.format(PATH, port, 22);
		ResponseEntity<ResponseError> response = restTemplate.getForEntity(url, ResponseError.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody().getMessage()).isEqualTo("No se encontraron resultados para el id: 22");
	}
	
	@Test
	public void add() throws IOException {
		final String url =  String.format(PATH, port, "");
		InputStream json = resourceLoader.getResource(JSON_PATH + "add.json").getInputStream();
		final PersonaDTO persona = MAPPER.readValue(json, PersonaDTO.class);
		
		ResponseEntity<PersonaDTO> response = restTemplate.postForEntity(url, persona, PersonaDTO.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response.getBody().getNombre()).isEqualTo("Luis Prdm");
		assertThat(response.getBody().getSexo()).isEqualTo("M");
		assertThat(response.getBody().getEmail()).isEqualTo("luis@mail.com");
	}
	
	@Test
	public void addBadRequest() throws IOException {
		final String url =  String.format(PATH, port, "");
		InputStream json = resourceLoader.getResource(JSON_PATH + "add-bad-request.json").getInputStream();
		final PersonaDTO persona = MAPPER.readValue(json, PersonaDTO.class);
		
		ResponseEntity<ResponseError> response = restTemplate.postForEntity(url, persona, ResponseError.class);
		assertThat(response.getBody().getMessage()).isEqualTo("El nombre es requerido");
	}

	@Test
	public void edit() throws IOException {
		final String url = String.format(PATH, port, 1);
		InputStream json = resourceLoader.getResource(JSON_PATH + "edit.json").getInputStream();
		PersonaDTO persona = MAPPER.readValue(json, PersonaDTO.class);
		HttpEntity<PersonaDTO> entity = new HttpEntity<>(persona);
		
		
		ResponseEntity<PersonaDTO> getResponse = restTemplate.getForEntity(url, PersonaDTO.class);
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(getResponse.getBody().getNombre()).isEqualTo("Alejandra Rodriguez Garcia");
		assertThat(getResponse.getBody().getTelefono()).isEqualTo("5532123663");
		
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
		
		getResponse = restTemplate.getForEntity(url, PersonaDTO.class);
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(getResponse.getBody().getNombre()).isEqualTo("Alejandra Garcia");
		assertThat(getResponse.getBody().getTelefono()).isEqualTo("5555555555");
		
	}
	
	@Test
	public void editBadRequest() throws IOException {
		final String url = String.format(PATH, port, 1);
		InputStream json = resourceLoader.getResource(JSON_PATH + "edit-bad-request.json").getInputStream();
		PersonaDTO persona = MAPPER.readValue(json, PersonaDTO.class);
		HttpEntity<PersonaDTO> entity = new HttpEntity<>(persona);
		
		ResponseEntity<ResponseError> response = restTemplate.exchange(url, HttpMethod.PUT, entity, ResponseError.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(response.getBody().getMessage()).isEqualTo("Fecha de nacimiento es requerida");
		
	}
	
	@Test
	public void editNotFound() throws IOException {
		final String url = String.format(PATH, port, 11);
		InputStream json = resourceLoader.getResource(JSON_PATH + "edit.json").getInputStream();
		PersonaDTO persona = MAPPER.readValue(json, PersonaDTO.class);
		HttpEntity<PersonaDTO> entity = new HttpEntity<>(persona);
	
		ResponseEntity<ResponseError> response = restTemplate.exchange(url, HttpMethod.PUT, entity, ResponseError.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody().getMessage()).isEqualTo("No se encontraron resultados para el id: 11");
	}
	
	@Test
	public void delete() {
		final String url = String.format(PATH, port, 3);
		
		ResponseEntity<PersonaDTO> get = restTemplate.getForEntity(url, PersonaDTO.class);
		assertThat(get.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
		
		get = restTemplate.getForEntity(url, PersonaDTO.class);
		assertThat(get.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void deleteNotFound() {
		final String url = String.format(PATH, port, 33);
				
		ResponseEntity<ResponseError> response = restTemplate.exchange(url, HttpMethod.DELETE, null, ResponseError.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody().getMessage()).isEqualTo("No se encontraron resultados para el id: 33");
		
	}
	
    
    
}
