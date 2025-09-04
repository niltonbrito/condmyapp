package com.bandampla.condmyapp.enums;

public enum Gender {

	SELECIONE(""),
	MASCULINO("MASCULINO"),
	FEMININO("FEMININO");
	
	private final String gender;
	
	private Gender(String gender) {
		this.gender = gender;
	}
}
