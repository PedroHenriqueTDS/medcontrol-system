package com.hospital.medcontrol.main;

import com.hospital.medcontrol.fachada.Fachada;

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