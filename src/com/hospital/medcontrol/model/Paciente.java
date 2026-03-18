package com.hospital.medcontrol.model;

public class Paciente {
    private final String nome;
    private final String cpf;
    private final String telefone;
    private PlanoDeSaude planoDeSaude;

    public Paciente(String nome, String cpf, String telefone, PlanoDeSaude planoDeSaude) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.planoDeSaude = planoDeSaude;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public PlanoDeSaude getPlanoDeSaude() {
        return planoDeSaude;
    }

    public void AtualizarPlanoDeSaude( PlanoDeSaude novoPlano) {
        this.planoDeSaude = novoPlano;
    }
    public double CalcularValorAPagar(double valorInternacao) {
        return planoDeSaude.calcularValorPago(valorInternacao);
    }
}
