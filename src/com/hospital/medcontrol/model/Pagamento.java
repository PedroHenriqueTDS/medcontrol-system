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

    private  double CalcularValorFinal(){
        if (formaPagamento == FormaPagamento.PIX_DINHEIRO) {
            return ValorOriginal * 0.9;
        } else if (formaPagamento == FormaPagamento.PARCELADO) {
            return ValorOriginal * 1.08;
        } else {
            return ValorOriginal;
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
