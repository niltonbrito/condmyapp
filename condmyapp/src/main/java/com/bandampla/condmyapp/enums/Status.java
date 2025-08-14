package com.bandampla.condmyapp.enums;

public enum Status {

	SELECIONE(""),
	ATIVO("ATIVO"),
	INATIVO("INATIVA"),
	BLOQUEADO("BLOQUEADO");
	
	private final String status;
	
	private Status(String status) {
		this.status = status;
	}
}
