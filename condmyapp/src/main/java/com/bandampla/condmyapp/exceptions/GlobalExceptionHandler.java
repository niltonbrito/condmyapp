package com.bandampla.condmyapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<Object> buildResponse(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }

    // ---------- ERROS DE NEGÓCIO ----------
    @ExceptionHandler(DuplicatedCpfException.class)
    public ResponseEntity<Object> handleDuplicatedCpf(DuplicatedCpfException ex) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(DuplicatedEmailException.class)
    public ResponseEntity<Object> handleDuplicatedEmail(DuplicatedEmailException ex) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(DuplicatedUsernameException.class)
    public ResponseEntity<Object> handleDuplicatedUsername(DuplicatedUsernameException ex) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(SenhasNaoCoincidemException.class)
    public ResponseEntity<Object> handleSenhasNaoCoincidem(SenhasNaoCoincidemException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(SenhaFracaException.class)
    public ResponseEntity<Object> handleSenhaFraca(SenhaFracaException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(UsuarioBloqueadoException.class)
    public ResponseEntity<Object> handleUsuarioBloqueado(UsuarioBloqueadoException ex) {
        return buildResponse(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler(UsuarioInativoException.class)
    public ResponseEntity<Object> handleUsuarioInativo(UsuarioInativoException ex) {
        return buildResponse(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler(TentativasLoginExcedidasException.class)
    public ResponseEntity<Object> handleTentativasLoginExcedidas(TentativasLoginExcedidasException ex) {
        return buildResponse(HttpStatus.LOCKED, ex.getMessage());
    }

    @ExceptionHandler(UsuarioMenorDeIdadeException.class)
    public ResponseEntity<Object> handleUsuarioMenorIdade(UsuarioMenorDeIdadeException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // ---------- ERROS GENÉRICOS ----------
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntime(RuntimeException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro inesperado: " + ex.getMessage());
    }
}
