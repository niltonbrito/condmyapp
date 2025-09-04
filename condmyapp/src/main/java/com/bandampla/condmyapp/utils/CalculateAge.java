package com.bandampla.condmyapp.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class CalculateAge {

    public static int calcularIdade(Date date) {
        if (date == null) return 0;

        LocalDate dataNasc = date.toInstant()
                                 .atZone(ZoneId.systemDefault())
                                 .toLocalDate();

        LocalDate dataAtual = LocalDate.now();
        return Period.between(dataNasc, dataAtual).getYears();
    }
}