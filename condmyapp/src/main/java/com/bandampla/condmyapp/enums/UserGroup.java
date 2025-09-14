package com.bandampla.condmyapp.enums;

public enum UserGroup {

	SELECIONE(""),
	ADMINISTRADOR("ADMINISTRADOR"),
	GERENTE("GERENTE"),
	MORADOR("MORADOR"),
	PROPRIETARIO("PROPRIETARIO"),
	SINDICO("SINDICO"),
	TERCEIRIZADA("TERCEIRIZADA"),;

    private final String group;

    UserGroup(String group) {
        this.group = group;
    }
}
