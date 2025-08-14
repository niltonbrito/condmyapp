package com.bandampla.condmyapp.enums;

public enum Group {

	SELECIONE(""),
	ADMINISTRADOR("ADMINISTRADOR"),
	GERENTE("GERENTE"),
	MORADOR("MORADOR"),
	PROPRIETARIO("PROPRIETARIO"),
	SINDICO("SINDICO"),
	TERCEIRIZADA("TERCEIRIZADA"),
	FORNECEDOR("FORNECEDOR");

    private final String group;

    Group(String group) {
        this.group = group;
    }
}
