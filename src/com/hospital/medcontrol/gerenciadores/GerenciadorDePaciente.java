package com.hospital.medcontrol.gerenciadores;

import com.hospital.medcontrol.model.Paciente;
import com.hospital.medcontrol.model.PlanoDeSaude;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorDePaciente {
    private List<Paciente> pacientes;

    public GerenciadorDePaciente(List<Paciente> pacientes) {
        List novoPacientes = new ArrayList();
        this.pacientes = pacientes;
    }

    public void cadastrarPaciente(String nome, String cpf, String telefone, PlanoDeSaude planoDeSaude) {
        validarTexto(nome, "Nome");
        validarTexto(cpf, "CPF");
        validarTexto(telefone, "Telefone");
        Paciente novoPaciente = new Paciente(nome, cpf, telefone, planoDeSaude);
        pacientes.add(novoPaciente);
    }

    public Paciente localizarPaciente(String cpf) {
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
            paciente.AtualizarPlanoDeSaude(novoPlano);
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
            throw new IllegalArgumentException("Cpf não pode ser nulo ou vazio");
        }
        if (cpf.length() != 11) {
            throw new IllegalArgumentException("Cpf deve conter 11 digitos");
        }
        if (!cpf.matches("\\d+")) {
            throw new IllegalArgumentException("CPF deve conter apenas números.");
        }

    }

    private void validarTelefone(String telefone) {
        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("Telefon não pode ser nulo ou vazio");
        }
        if (telefone.length() != 11) {
            throw new IllegalArgumentException("Telefone deve conter 11 digitos");
        }
        if (!telefone.matches("\\d+")) {
            throw new IllegalArgumentException("Telefone deve conter apenas números.");
        }
    }
    private void validarTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(campo + " não pode ser nulo ou vazio");
        }
    }
}
