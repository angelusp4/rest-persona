package com.lapm.restpersona.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDTO<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9089460192413236435L;
	
	private T content;

}
