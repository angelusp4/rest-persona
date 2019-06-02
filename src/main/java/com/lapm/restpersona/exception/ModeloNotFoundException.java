package com.lapm.restpersona.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ModeloNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3560838655515585192L;
	


	public ModeloNotFoundException(String mensaje) {
		super(mensaje);
	}

}
