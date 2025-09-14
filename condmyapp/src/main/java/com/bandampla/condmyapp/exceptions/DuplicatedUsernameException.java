package com.bandampla.condmyapp.exceptions;

public class DuplicatedUsernameException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicatedUsernameException(String message) {
        super(message);
    }
}

