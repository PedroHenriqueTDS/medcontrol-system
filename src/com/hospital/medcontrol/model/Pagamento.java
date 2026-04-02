package com.hospital.medcontrol.model;

import com.hospital.medcontrol.enums.FormaPagamento;
import java.io.Serializable;

public class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private Internacao internacao;
    private double valorFinal;
    private FormaPagamento formaPagamento;

    public Pagamento(int id, Internacao internacao, FormaPagamento forma) {
        this.id = id;
        this.internacao = internacao;
        this.formaPagamento = forma;
        this.valorFinal = calcularValorFinal(internacao.calcularValorFinal(), forma);
    }

    private double calcularValorFinal(double valorBase, FormaPagamento forma) {
        if (forma == FormaPagamento.PIX_DINHEIRO) {
            return valorBase * 0.9;
        } else if (forma == FormaPagamento.PARCELADO) {
            return valorBase * 1.08;
        } else {
            return valorBase; // Cartao normal
        }
    }

    public int getId() { return id; }
    public double getValorFinal() { return valorFinal; }
    public Internacao getInternacao() { return internacao; }

    @Override
    public String toString() {
        return String.format("Pagamento ID: %d | Internacao ID: %d | CPF: %s | Forma: %s | Valor Final: R$ %.2f",
                id, internacao.getId(), internacao.getCpfPaciente(), formaPagamento, valorFinal);
    }
}