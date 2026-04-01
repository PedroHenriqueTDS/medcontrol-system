package com.hospital.medcontrol.fachada;

import com.hospital.medcontrol.gerenciadores.GerenciadorPagamentos;
import com.hospital.medcontrol.enums.FormaPagamento;

public class Fachada {

    private static GerenciadorPagamentos gerPagamentos = new GerenciadorPagamentos();

    public static void pagarInternacao(double valor, int opcao) {
        FormaPagamento forma;

        switch (opcao) {
            case 1:
                forma = FormaPagamento.PIX_DINHEIRO;
                break;
            case 2:
                forma = FormaPagamento.CARTAO;
                break;
            case 3:
                forma = FormaPagamento.PARCELADO;
                break;
            default:
                System.out.println("Opção inválida");
                return;
        }

        gerPagamentos.registrarPagamento(valor, forma);
        System.out.println("Pagamento realizado com sucesso!");
    }

    public static void relatorioPagamentos() {
        gerPagamentos.listarPagamentos();
    }
}