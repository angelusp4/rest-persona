package com.lapm.restpersona.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadParametersException extends RuntimeException{

	private static final long serialVersionUID = -54336197919205154L;
	

	
	public BadParametersException(String mensaje) {
		super(mensaje);
	}

}
