package com.hospital.medcontrol.gerenciadores;

import com.hospital.medcontrol.model.Paciente;
import com.hospital.medcontrol.model.PlanoDeSaude;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorDePaciente {
    private List<Paciente> pacientes;

    public GerenciadorDePaciente() {
        this.pacientes = new ArrayList<>();
    }

    public void cadastrarPaciente(String nome, String cpf, String telefone, PlanoDeSaude planoDeSaude) {
        validarTexto(nome, "Nome");
        validarCpf(cpf);
        validarTelefone(telefone);

        if (cpfJaCadastrado(cpf)) {
            throw new IllegalArgumentException("Já existe paciente cadastrado com esse CPF.");
        }

        Paciente novoPaciente = new Paciente(nome, cpf, telefone, planoDeSaude);
        pacientes.add(novoPaciente);
    }

    public Paciente localizarPaciente(String cpf) {
        validarCpf(cpf);

        for (Paciente paciente : pacientes) {
            if (paciente.getCpf().equals(cpf)) {
                return paciente;
            }
        }
        return null;
    }

    public void atualizarPlanoSaude(String cpf, PlanoDeSaude novoPlano) {
        Paciente paciente = localizarPaciente(cpf);

        if (paciente != null) {
            paciente.atualizarPlanoDeSaude(novoPlano);
        }
    }

    private boolean cpfJaCadastrado(String cpf) {
        for (Paciente paciente : pacientes) {
            if (paciente.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    private void validarCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio.");
        }

        if (cpf.length() != 11) {
            throw new IllegalArgumentException("CPF deve conter 11 dígitos.");
        }

        if (!cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF deve conter apenas números.");
        }
    }

    private void validarTelefone(String telefone) {
        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("Telefone não pode ser nulo ou vazio.");
        }

        if (telefone.length() != 11) {
            throw new IllegalArgumentException("Telefone deve conter 11 dígitos.");
        }

        if (!telefone.matches("\\d{11}")) {
            throw new IllegalArgumentException("Telefone deve conter apenas números.");
        }
    }

    private void validarTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(campo + " não pode ser nulo ou vazio.");
        }
    }
}