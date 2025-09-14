package com.bandampla.condmyapp.utils;

import org.springframework.stereotype.Component;

@Component("maskUtils")
public class MaskUtils {

    public static String maskCpfCripto(String cpf) {
        if (cpf.isEmpty() || cpf.trim().length() != 11) {
            return cpf; // retorna sem alteração se for inválido
        }
        return cpf.substring(0, 3) + "." +
               cpf.substring(3, 4) + "**.***-**";
    }
    
    public static String maskCpf(String cpf) {
        if (cpf.isEmpty() || cpf.trim().length() != 11) {
            return cpf; // Retorna sem alteração se o CPF não tiver 11 dígitos
        }
        return cpf.substring(0, 3) + "." +
               cpf.substring(3, 6) + "." +
               cpf.substring(6, 9) + "-" +
               cpf.substring(9, 11);
    }

    public static String maskPhone(String phone) {
        if (phone == null ||phone.isBlank()) return "";

        String digits = phone.replaceAll("\\D", "");

        if (digits.length() == 10) {
            return String.format("(%s) %s-%s",
                    digits.substring(0, 2),
                    digits.substring(2, 6),
                    digits.substring(6));
        } else if (digits.length() == 11) {
            return String.format("(%s) %s-%s",
                    digits.substring(0, 2),
                    digits.substring(2, 7),
                    digits.substring(7));
        }

        return phone;
    }
}