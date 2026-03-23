package com.hospital.medcontrol.gerenciadores;
import com.hospital.medcontrol.model.Internacao;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorInternacaes {
    private List<Internacao> list= new ArrayList<>();

    public void registrarInternacao(Internacao i) {
        list.add(i);
    }

    public void registarAlta(int id, java.time.LocalDate dataAlta) {
        for (Internacao i : list) {
            if (i.isAtiva()) {
                i.registrarAlta(dataAlta);
            }

        }
    }

    public void listarAtivas(){
        for (Internacao i : list ){
            if (i.isAtiva()) {
                System.out.println(i);
            }
        }
    }
    public void localizarPaciente() {
        for (Internacao i : list) {
            if (!i.isAtiva()){
                System.out.println(i);
                System.out.println("Valor: R$ " + i.calcularValor());
            }
        }
    }
}
