package com.hospital.medcontrol.gerenciadores;

import com.hospital.medcontrol.exceptions.CRMInvalidoException;
import com.hospital.medcontrol.exceptions.MedicoJaExisteException;
import com.hospital.medcontrol.exceptions.MedicoNaoEncontradoException;
import com.hospital.medcontrol.model.Medico;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorMedicos {
    List<Medico> medicos = new ArrayList<Medico>();

    public void cadastrarMedico(Medico medico) {
        if (!medico.getCrm().matches("\\d{6}/[A-Z]{2}")) {
            throw new CRMInvalidoException();
        }
        for (Medico m : medicos) {
            if (m.getCrm().equals(medico.getCrm())) {
                throw new MedicoJaExisteException();
            }
        }
        medicos.add(medico);
    }
    public void removerMedico(String crm) {
        Medico medicoRemover = null;
        for(Medico m : medicos) {
            if (m.getCrm().equals(crm)){
                medicoRemover = m;
                break;
            }
        }
        if(medicoRemover == null) {
            throw new MedicoNaoEncontradoException();
        }
        medicos.remove(medicoRemover);
    }

    public Medico buscarMedico(String crm) {
        for (Medico m : medicos) {
            if (m.getCrm().equals(crm)) {
                return m;
            }
        }
        throw new MedicoNaoEncontradoException();
    }

    public void desativarMedico(String crm) {
        for (Medico m : medicos) {
            if (m.getCrm().equals(crm)) {
                m.setAtivo(false);
            }
        }
    }


    public void AtivarMedico(String crm) {
        for (Medico m : medicos) {
           m.setAtivo(true);
        }
    }
    public void listarMedicos() {
        if(medicos.isEmpty()) {
            System.out.println("Nenhum médico cadastrado.");
            return;
        }
        //talvez eu coloque um print aqui, to pensando
        for (Medico m : medicos) {
            System.out.println(m);
        }
    }
}

//selo Peu de qualidade(vale nada)