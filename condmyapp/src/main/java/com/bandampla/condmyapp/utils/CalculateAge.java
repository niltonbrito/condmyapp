package com.bandampla.condmyapp.utils;

import java.time.LocalDate;
import java.time.Period;

public class CalculateAge {

    public static int calcularIdade(LocalDate birthDate) {
        if (birthDate == null) return 0;

        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}