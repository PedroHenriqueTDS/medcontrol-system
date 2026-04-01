package com.hospital.medcontrol.model;

import com.hospital.medcontrol.enums.Especialidade;
import java.io.Serializable;

public class Medico implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String crm;
    private Especialidade especialidade;
    private boolean ativo;

    public Medico(String nome, String crm, Especialidade especialidade) {
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.ativo = true;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCrm() { return crm; }
    public Especialidade getEspecialidade() { return especialidade; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    @Override
    public String toString() {
        return String.format("Médico: %s | CRM: %s | Especialidade: %s | Status: %s",
                nome, crm, especialidade, (ativo ? "Ativo" : "Inativo"));
    }
}