package com.hospital.medcontrol.exceptions;

public class CRMInvalidoException extends RuntimeException {

    public CRMInvalidoException() {
        super("CRM inválido! Use o formato: 123456/UF");
    }
    public CRMInvalidoException(String message) {
        super(message);
    }
}
