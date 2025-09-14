package com.bandampla.condmyapp.exceptions;

public class DuplicatedCpfException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicatedCpfException(String message) {
        super(message);
    }
}

