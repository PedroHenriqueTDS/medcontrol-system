package com.hospital.medcontrol.gerenciadores;

import com.hospital.medcontrol.model.Internacao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorInternacaes implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Internacao> internacoes = new ArrayList<>();
    private int contadorId = 1;

    public int gerarNovoId() { return contadorId++; }

    public void registrarInternacao(Internacao i) { internacoes.add(i); }

    public void registarAlta(int id, java.time.LocalDate dataAlta) {
        for (Internacao i : internacoes) {
            if (i.getId() == id && i.isAtiva()) {
                i.registrarAlta(dataAlta);
                System.out.println("Alta confirmada. Valor a pagar: R$ " + i.calcularValorFinal());
                return;
            }
        }
        System.out.println("Internação ativa não encontrada para este ID.");
    }

    public void listarAtivas() {
        internacoes.stream()
                .filter(Internacao::isAtiva)
                .sorted((a, b) -> b.getDataEntrada().compareTo(a.getDataEntrada()))
                .forEach(System.out::println);
    }

    public void listarConcluidas() {
        internacoes.stream()
                .filter(i -> !i.isAtiva())
                .forEach(i -> System.out.println(
                        String.format("ID: %d | Leito: %s | CPF: %s | Médico: %s | Período: %s a %s",
                                i.getId(), i.toString().split("\\|")[2].trim(), i.getCpfPaciente(),
                                i.toString().split("\\|")[1].trim(), i.getDataEntrada(), i.getDataAlta())
                ));
    }

    public Internacao buscarPorId(int id) {
        for (Internacao i : internacoes) {
            if (i.getId() == id) return i;
        }
        return null;
    }

    public List<Internacao> buscarPorCpf(String cpf) {
        List<Internacao> resultado = new ArrayList<>();
        for (Internacao i : internacoes) {
            if (i.getCpfPaciente().equals(cpf)) resultado.add(i);
        }
        return resultado;
    }
}