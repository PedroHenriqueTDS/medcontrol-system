package com.hospital.medcontrol.gerenciadores;

import com.hospital.medcontrol.model.Pagamento;
import com.hospital.medcontrol.model.Internacao;
import com.hospital.medcontrol.enums.FormaPagamento;
import java.io.Serializable;
import java.util.ArrayList;

public class GerenciadorPagamentos implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Pagamento> pagamentos = new ArrayList<>();
    private int contadorId = 1;

    public void registrarPagamento(Internacao internacao, FormaPagamento forma) {
        Pagamento pagamento = new Pagamento(contadorId++, internacao, forma);
        pagamentos.add(pagamento);
    }

    public void listarPagamentos() {
        double totalRecebido = 0;

        System.out.println("--- RELATÓRIO DE PAGAMENTOS ---");
        for (Pagamento p : pagamentos) {
            System.out.println(p);
            totalRecebido += p.getValorFinal();
        }
        System.out.println("Total Geral Recebido: R$ " + totalRecebido);

        try (java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter("relatorio_pagamentos.txt"))) {
            writer.println("=== RELATÓRIO OFICIAL DE PAGAMENTOS ===");
            for (Pagamento p : pagamentos) {
                writer.println(p.toString());
            }
            writer.println("---------------------------------------");
            writer.println("TOTAL GERAL RECEBIDO: R$ " + totalRecebido);
            System.out.println("\nAviso: Relatório salvo no arquivo 'relatorio_pagamentos.txt'.");
        } catch (java.io.IOException e) {
            System.out.println("Erro ao gerar arquivo TXT: " + e.getMessage());
        }
    }
}