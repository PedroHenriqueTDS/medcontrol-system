package com.hospital.medcontrol.exceptions;

public class MedicoNaoEncontradoException extends RuntimeException {

    public MedicoNaoEncontradoException() {
        super("Médico não encontrado no sistema.");
    }
    public MedicoNaoEncontradoException(String message) {
        super(message);
    }
}
