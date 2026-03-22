package com.hospital.medcontrol.model;
import com.hospital.medcontrol.enums.TipoInternacao;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Internacao {
    private int id;
    private String paciente;
    private String medico;
    private TipoInternacao tipoInternacao;
    private String quarto;
    private LocalDate dataEntrada;
    private LocalDate dataAlta;
    private String tipoPlano;

    public Internacao(int id, String paciente, String medico , TipoInternacao tipoInternacao, String quarto , LocalDate dataEntrada , String tipoPlano){
        this.id=id;
        this.paciente=paciente;
        this.medico=medico;
        this.tipoInternacao=tipoInternacao;
        this.quarto=quarto;
        this.dataEntrada=dataEntrada;
        this.tipoPlano=tipoPlano;
    }
    public void registrarAlta(LocalDate dataAlta) {
        this.dataAlta = dataAlta;
    }
    public long calcularDias(){
        if (dataAlta == null) return 0;
        return ChronoUnit.DAYS.between(dataEntrada,dataAlta);
    }
    public double calcularValor() {
        long dias = calcularDias();

        double diaria = 0;

        switch (tipoInternacao){
            case ENFERMEIRA:
                diaria = 300;
                break;
            case APARTAMENTO:
                diaria = 800 ;
                break;
            case UTI:
                diaria = 2500;
                break;
        }

        double total = dias * diaria;

        if (tipoPlano.equalsIgnoreCase("enfermaria")){
            total *=0.8;
        }else if (tipoPlano.equalsIgnoreCase("apartamento")) {
            total *= 0.9 ;
        }

        return total;


    }
    public boolean isAtiva() {
        return dataAlta == null;
    }

    public String getPaciente() {
        return paciente;
    }
    public int getId() {
        return id;
    }

    public String toString() {
        return "ID: " + id +
                " | Paciente: " + paciente +
                " | Leito: " + tipoInternacao +
                " | Entrada: " + dataEntrada +
                " | Alta: " + dataAlta;

    }
}
