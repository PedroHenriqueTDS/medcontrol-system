package com.hospital.medcontrol.model;

import com.hospital.medcontrol.enums.FormaPagamento;
import java.io.Serializable;

public class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private double valorOriginal;
    private double valorFinal;
    private FormaPagamento formaPagamento;

    public Pagamento(int id, double valorOriginal, double valorFinal, FormaPagamento formaPagamento) {
        this.id = id;
        this.valorOriginal = valorOriginal;
        this.valorFinal = valorFinal;
        this.formaPagamento = formaPagamento;
    }

    public Pagamento(int id, double valor, FormaPagamento forma) {
        this.id = id;
        this.valorOriginal = valor;
        this.formaPagamento = forma;
        this.valorFinal = calcularValorFinal(valor, forma);
    }

    private double calcularValorFinal(double valor, FormaPagamento forma) {
        if (forma == FormaPagamento.PIX_DINHEIRO) {
            return valor * 0.9;
        } else if (forma == FormaPagamento.PARCELADO) {
            return valor * 1.08;
        } else {
            return valor;
        }
    }

    public int getId() { return id; }
    public double getValorFinal() { return valorFinal; }

    @Override
    public String toString() {
        return String.format("Pagamento [ID: %d | Forma: %s | Valor Final: R$ %.2f]",
                id, formaPagamento, valorFinal);
    }
}