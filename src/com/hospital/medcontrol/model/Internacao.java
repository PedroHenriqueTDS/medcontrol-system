package com.hospital.medcontrol.model;

import com.hospital.medcontrol.enums.TipoLeito;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Internacao implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String cpfPaciente;
    private String crmMedico;
    private TipoLeito tipoLeito;
    private String quarto;
    private LocalDate dataEntrada;
    private LocalDate dataAlta;
    private double percentualCoparticipacao;

    public Internacao(int id, String cpfPaciente, String crmMedico, TipoLeito tipoLeito, String quarto, LocalDate dataEntrada, double percentualCoparticipacao) {
        this.id = id;
        this.cpfPaciente = cpfPaciente;
        this.crmMedico = crmMedico;
        this.tipoLeito = tipoLeito;
        this.quarto = quarto;
        this.dataEntrada = dataEntrada;
        this.percentualCoparticipacao = percentualCoparticipacao;
    }

    public double calcularValorFinal() {
        if (dataAlta == null) return 0;
        long dias = ChronoUnit.DAYS.between(dataEntrada, dataAlta);
        if (dias <= 0) dias = 1;

        double valorDiaria = switch (tipoLeito) {
            case ENFERMARIA -> 320.0;
            case APARTAMENTO -> 850.0;
            case UTI -> 2500.0;
        };

        double valorTotalHospital = dias * valorDiaria;
        return valorTotalHospital * percentualCoparticipacao;
    }

    public void registrarAlta(LocalDate dataAlta) { this.dataAlta = dataAlta; }
    public boolean isAtiva() { return dataAlta == null; }

    // Getters para os relatórios
    public int getId() { return id; }
    public String getCpfPaciente() { return cpfPaciente; }
    public String getCrmMedico() { return crmMedico; }
    public TipoLeito getTipoLeito() { return tipoLeito; }
    public LocalDate getDataEntrada() { return dataEntrada; }
    public LocalDate getDataAlta() { return dataAlta; }

    @Override
    public String toString() {
        return String.format("ID: %d | CPF: %s | Medico: %s | Leito: %s | Entrada: %s | Alta: %s",
                id, cpfPaciente, crmMedico, tipoLeito, dataEntrada, (dataAlta != null ? dataAlta : "Ativa"));
    }
}