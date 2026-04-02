package com.hospital.medcontrol.gerenciadores;

import com.hospital.medcontrol.model.Medico;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class GerenciadorMedicos implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Medico> medicos = new ArrayList<>();

    public void cadastrarMedico(Medico medico) {
        if (!medico.getCrm().matches("\\d{6}/[A-Z]{2}")) {
            throw new CRMInvalidoException();
        }
        for (Medico m : medicos) {
            if (m.getCrm().equalsIgnoreCase(medico.getCrm())) {
                throw new MedicoJaExisteException();
            }
        }
        medicos.add(medico);
    }

    public Medico buscarMedico(String crm) {
        for (Medico m : medicos) {
            if (m.getCrm().equalsIgnoreCase(crm)) return m;
        }
        throw new MedicoNaoEncontradoException();
    }

    public void removerMedico(String crm) {
        Medico m = buscarMedico(crm);
        m.setAtivo(false);
    }

    public void ativarMedico(String crm) {
        Medico m = buscarMedico(crm);
        m.setAtivo(true);
    }

    public List<Medico> listarMedicosAtivos() {
        List<Medico> ativos = new ArrayList<>();
        for (Medico m : medicos) {
            if (m.isAtivo()) ativos.add(m);
        }
        return ativos;
    }

    public void imprimirTodos() {
        if (medicos.isEmpty()) {
            System.out.println("Nenhum médico cadastrado.");
            return;
        }
        for (Medico m : medicos) {
            if (m.isAtivo()) System.out.println(m);
        }
    }

    public static class CRMInvalidoException extends RuntimeException {
        public CRMInvalidoException() { super("CRM inválido! Formato correto: 123456/UF"); }
    }
    public static class MedicoJaExisteException extends RuntimeException {
        public MedicoJaExisteException() { super("Médico já cadastrado com este CRM."); }
    }
    public static class MedicoNaoEncontradoException extends RuntimeException {
        public MedicoNaoEncontradoException() { super("Médico não encontrado no sistema."); }
    }
}