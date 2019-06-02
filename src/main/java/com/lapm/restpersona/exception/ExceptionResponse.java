package com.lapm.restpersona.exception;

import lombok.Data;

@Data
public class ExceptionResponse {

	private String type;
	private int status;
	private String code;
	private String helpUrl;
	private String message;
	
	
	public ExceptionResponse(String type, int status, String code, String helpUrl, String message) {
		super();
		this.type = type;
		this.status = status;
		this.code = code;
		this.helpUrl = helpUrl;
		this.message = message;
	}

	

}
