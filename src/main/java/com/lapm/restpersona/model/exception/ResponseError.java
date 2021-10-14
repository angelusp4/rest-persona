package com.lapm.restpersona.model.exception;

import lombok.Data;

@Data
public class ResponseError {

	private String type;
	private int status;
	private String code;
	private String helpUrl;
	private String message;
	
	
	public ResponseError(String type, int status, String code, String helpUrl, String message) {
		super();
		this.type = type;
		this.status = status;
		this.code = code;
		this.helpUrl = helpUrl;
		this.message = message;
	}

	

}
