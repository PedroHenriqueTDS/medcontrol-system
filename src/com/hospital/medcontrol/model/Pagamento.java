package com.hospital.medcontrol.model;

import com.hospital.medcontrol.enums.FormaPagamento;

public class Pagamento {

    private int id;
    private double ValorOriginal;
    private double ValorFinal;
    private FormaPagamento formaPagamento;

    public Pagamento(int id, double valorOriginal, double valorFinal, FormaPagamento formaPagamento) {
        this.id = id;
        this.ValorOriginal = valorOriginal;
        this.ValorFinal = valorFinal;
        this.formaPagamento = formaPagamento;
    }

    public Pagamento(int id, double valor, FormaPagamento forma) {
        this.id = id;
        this.ValorOriginal = valor;
        this.formaPagamento = forma;
        this.ValorFinal = calcularValorFinal(valor, forma);
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

    public int getId() {
        return id;
    }

    public double getValorFinal() {
        return ValorFinal;
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", formaPagamento=" + formaPagamento +
                ", ValorFinal=" + ValorFinal +
                '}';
    }
}
