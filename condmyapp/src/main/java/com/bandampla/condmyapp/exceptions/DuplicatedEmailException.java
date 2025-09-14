package com.bandampla.condmyapp.exceptions;

public class DuplicatedEmailException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicatedEmailException(String message) {
        super(message);
    }
}

