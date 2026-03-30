package com.hospital.medcontrol.model;

import com.hospital.medcontrol.enums.TipoLeito;

public class PlanoDeSaude {
    private String Operadora;
    private TipoLeito tipoLeitoPermitida;
    private double percentualPagamento;

    public PlanoDeSaude(String operadora, TipoLeito tipoLeitoPermitida, double percentualPagamento) {
        Operadora = operadora;
        this.tipoLeitoPermitida = tipoLeitoPermitida;
        this.percentualPagamento = percentualPagamento;
    }

    private void validarDados(String operadora, TipoLeito tipoLeitoPermitida, double percentualPagamento) {
        if (operadora == null || operadora.isBlank()) {
            throw new IllegalArgumentException("Operadora não pode ser nula ou vazia.");
        }

        if (tipoLeitoPermitida == null) {
            throw new IllegalArgumentException("Tipo de internação é obrigatório.");
        }

        if (percentualPagamento != 0.0 && percentualPagamento != 0.15 && percentualPagamento != 1.0) {
            throw new IllegalArgumentException("Percentual deve ser 0.0, 0.15 ou 1.0.");
        }

        if (operadora.equals("Particular") && percentualPagamento != 1.0) {
            throw new IllegalArgumentException("Particular deve pagar 100%.");
        }

        if (!operadora.equals("Particular") && percentualPagamento == 1.0) {
            throw new IllegalArgumentException("Somente Particular pode ter percentual 1.0.");
        }
    }

    public String getOperadora() {
        return Operadora;
    }

    public TipoLeito getTipoInternacaoPermitida() {
        return tipoLeitoPermitida;
    }

    public double getPercentualPagamento() {
        return percentualPagamento;
    }
    public double calcularValorPago(double valorInternacao) {
        return valorInternacao * percentualPagamento;
    }
}
