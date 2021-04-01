package com.deeconsulting.crud.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -4814102879723161621L;
	
	public ResourceNotFoundException(String exception) {
		super(exception);
	}

}
