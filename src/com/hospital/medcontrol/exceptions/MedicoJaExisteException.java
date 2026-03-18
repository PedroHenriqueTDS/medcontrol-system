package com.hospital.medcontrol.exceptions;

public class MedicoJaExisteException extends RuntimeException {

    public MedicoJaExisteException() {
        super("Médico já cadastrado no sistema.");
    }
    public MedicoJaExisteException(String message) {
        super(message);
    }
}
