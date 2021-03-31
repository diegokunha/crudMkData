package com.deeconsulting.crud.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4038699449172083015L;

	public ResourceNotFoundException(String exception) {
		super(exception);
	}

}
