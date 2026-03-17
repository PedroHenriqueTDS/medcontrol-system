package com.hospital.medcontrol.gerenciadores;

import com.hospital.medcontrol.enums.TipoInternacao;

public class PlanoDeSaude {
    private String Operadora;
    private TipoInternacao tipoInternacaoPermitida;
    private double percentualPagamento;

    public PlanoDeSaude(String operadora, TipoInternacao tipoInternacaoPermitida, double percentualPagamento) {
        Operadora = operadora;
        this.tipoInternacaoPermitida = tipoInternacaoPermitida;
        this.percentualPagamento = percentualPagamento;
    }

    public String getOperadora() {
        return Operadora;
    }

    public TipoInternacao getTipoInternacaoPermitida() {
        return tipoInternacaoPermitida;
    }

    public double getPercentualPagamento() {
        return percentualPagamento;
    }
    public double calcularValorPago(double valorInternacao) {
        return valorInternacao * percentualPagamento;
    }
}
