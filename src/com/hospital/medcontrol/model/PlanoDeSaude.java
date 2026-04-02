package com.hospital.medcontrol.model;

import com.hospital.medcontrol.enums.TipoLeito;
import java.io.Serializable;

public class PlanoDeSaude implements Serializable {
    private static final long serialVersionUID = 1L;

    private String operadora;
    private TipoLeito tipoLeitoPermitido;
    private double percentualPagamento;

    public PlanoDeSaude(String operadora, TipoLeito tipoLeitoPermitido, double percentualPagamento) {
        validarDados(operadora, tipoLeitoPermitido, percentualPagamento);
        this.operadora = operadora;
        this.tipoLeitoPermitido = tipoLeitoPermitido;
        this.percentualPagamento = percentualPagamento;
    }

    private void validarDados(String operadora, TipoLeito tipoLeitoPermitido, double percentualPagamento) {
        if (operadora == null || operadora.isBlank()) {
            throw new IllegalArgumentException("Operadora não pode ser nula ou vazia.");
        }
        if (tipoLeitoPermitido == null) {
            throw new IllegalArgumentException("Tipo de internação é obrigatório.");
        }
        if (percentualPagamento != 0.0 && percentualPagamento != 0.15 && percentualPagamento != 1.0) {
            throw new IllegalArgumentException("Percentual deve ser 0.0, 0.15 ou 1.0.");
        }
        if (operadora.equalsIgnoreCase("Particular") && percentualPagamento != 1.0) {
            throw new IllegalArgumentException("Particular deve pagar 100%.");
        }
        if (!operadora.equalsIgnoreCase("Particular") && percentualPagamento == 1.0) {
            throw new IllegalArgumentException("Somente Particular pode ter percentual 1.0.");
        }
    }

    public String getOperadora() { return operadora; }
    public TipoLeito getTipoInternacaoPermitida() { return tipoLeitoPermitido; }
    public double getPercentualPagamento() { return percentualPagamento; }
    public double calcularValorPago(double valorInternacao) { return valorInternacao * percentualPagamento; }

    @Override
    public String toString() {
        return "PlanoDeSaude{operadora='" + operadora + "', tipoLeitoPermitido=" + tipoLeitoPermitido + ", percentualPagamento=" + percentualPagamento + '}';
    }
}