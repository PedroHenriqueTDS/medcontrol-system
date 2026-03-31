package com.hospital.medcontrol.model;

public class Paciente {
    private String nome;
    private String cpf;
    private String telefone;
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

    public void atualizarPlanoDeSaude(PlanoDeSaude novoPlano) {
        this.planoDeSaude = novoPlano;
    }

    public double calcularValorAPagar(double valorInternacao) {
        return planoDeSaude.calcularValorPago(valorInternacao);
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", planoDeSaude=" + planoDeSaude +
                '}';
    }
}