package com.hospital.medcontrol.model;

import com.hospital.medcontrol.enums.Especialidade;

public class Medico {
    private String nome;
    private String crm;
    private Especialidade  especialidade;
    private boolean ativo;

    public Medico(String nome, String crm, Especialidade especialidade) {
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.ativo = true;
    }

    public String getNome() {
        return nome;
    }
    public String getCrm() {
        return crm;
    }
    public Especialidade getEspecialidade() {
        return especialidade;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "nome='" + nome + '\'' +
                ", crm='" + crm + '\'' +
                ", especialidade=" + especialidade +
                ", ativo=" + ativo +
                '}';
    }
}
