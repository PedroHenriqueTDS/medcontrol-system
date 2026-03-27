package com.hospital.medcontrol.main;

import com.hospital.medcontrol.fachada.Fachada;

import javax.sound.midi.spi.SoundbankReader;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Fachada fachada = new Fachada();

        int opcao;

        do {
            System.out.println("\n=== SISTEMA HOSPITALAR ===");
            System.out.println("1 - Cadastrar paciente");
            System.out.println("2 - Buscar paciente");
            System.out.println("3 - Cadastrar médico");
            System.out.println("4 - Registrar internação");
            System.out.println("5 - Dar alta");
            System.out.println("6 - Pagar internação");
            System.out.println("7 - Relatórios");
            System.out.println("0 - Sair");

            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            switch (opcao) {

                case 1:
                case 2:
                case 3:
                case 4:
                    System.out.println("id:");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Paciente:");
                    String Paciente = sc.nextLine();

                    System.out.print("Medico:");
                    String Medico = sc.nextLine();

                    System.out.println("tipo de Internacao ENFERMARIA,APARTAMENTO,UTI");
                    String Internacao = sc.nextLine();

                    System.out.println("quarto:");
                    String qaurto = sc.nextLine();

                        System.out.println("Plano ENFERMARIA , APARTAMENTO , PARTICULAR:");
                    String plano = sc.nextLine();

                    break;



                case 5:
                    System.out.println("Funcionalidade ainda não implementada.");
                    break;

                case 6:
                    System.out.print("Digite o valor da internação: ");
                    double valor = sc.nextDouble();

                    System.out.println("Forma de pagamento:");
                    System.out.println("1 - PIX/Dinheiro (10% desconto)");
                    System.out.println("2 - Cartão (valor normal)");
                    System.out.println("3 - Parcelado (8% juros)");

                    int forma = sc.nextInt();

                    fachada.pagarInternacao(valor, forma);
                    break;

                case 7:
                    System.out.println("\n--- RELATÓRIO DE PAGAMENTOS ---");
                    fachada.relatorioPagamentos();
                    break;
                case 0:
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
}