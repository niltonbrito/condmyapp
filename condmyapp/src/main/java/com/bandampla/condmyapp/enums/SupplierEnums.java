package com.bandampla.condmyapp.enums;

public enum SupplierEnums {

	SELECIONE(""),
	TERCEIRIZADA("TERCEIRIZADA"),
	FORNECEDOR("FORNECEDOR");

    private final String supplierEnums;

    SupplierEnums(String supplierEnums) {
        this.supplierEnums = supplierEnums;
    }
}
