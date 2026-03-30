package com.hospital.medcontrol.main;

import com.hospital.medcontrol.enums.TipoLeito;
import com.hospital.medcontrol.model.Paciente;
import view.Hospital;
import com.hospital.medcontrol.enums.Especialidade;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String nomeArquivo = "hospital.ser";

        // Tenta carregar os dados salvos anteriormente
        Hospital hospital = Hospital.carregarHospital(nomeArquivo);

        // Se o arquivo não existir (primeira vez), cria um novo objeto Hospital
        if (hospital == null) {
            hospital = new Hospital();
        }

        int opcao;

        do {
            System.out.println("\n=== SISTEMA HOSPITALAR ===");
            System.out.println("1 - Cadastrar paciente");
            System.out.println("2 - Buscar paciente");
            System.out.println("3 - Cadastrar médico ");
            System.out.println("4 - Registrar internação");
            System.out.println("5 - Dar alta");
            System.out.println("6 - Pagar internação");
            System.out.println("7 - Relatórios");
            System.out.println("0 - Sair e Salvar");

            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1: {
                    System.out.println("\nCADASTRO DE PACIENTE ");
                    sc.nextLine();

                    System.out.println("Nome do Paciente: ");
                    nomeArquivo = sc.nextLine();

                    System.out.println("CPF do Paciente(11 digitos): ");
                    sc.nextDouble();

                    System.out.println("Telefone (11 digitos): ");
                    sc.nextDouble();
                    break;
                }

                case 2: {

                    System.out.println("\nBUSCAR PACIENTE");
                    sc.nextLine();

                    System.out.print("Digite o CPF: ");
                    String cpfBusca = sc.nextLine();

                    Paciente paciente = hospital.localizarPaciente(cpfBusca);

                    if (paciente != null) {
                        System.out.println("Paciente encontrado:");
                        System.out.println("Nome: " + paciente.getNome());
                        System.out.println("CPF: " + paciente.getCpf());
                        System.out.println("Telefone: " + paciente.getTelefone());
                    } else {
                        System.out.println("Paciente não encontrado.");
                    }

                    break;
                }

                case 3: {
                    // módulo de medicos(peu)
                    System.out.println("\nCADASTRO DE MÉDICO ");
                    sc.nextLine();

                    System.out.print("Nome do Médico: ");
                    String nomeM = sc.nextLine();

                    System.out.print("CRM (formato 123456/UF): ");
                    String crmM = sc.nextLine();

                    System.out.println("Selecione a Especialidade:");
                    System.out.println("1 - Clínica Médica Geral");
                    System.out.println("2 - Cardiologia");
                    System.out.println("3 - Geriatria");
                    System.out.println("4 - Ortopedia");
                    System.out.println("5 - Cirurgia");
                    System.out.println("6 - Medicina Intensiva");
                    System.out.print("Opção: ");
                    int espOpcao = sc.nextInt();

                    Especialidade especialidade = null;
                    switch (espOpcao) {
                        case 1 -> especialidade = Especialidade.CLINICA_MEDICA_GERAL;
                        case 2 -> especialidade = Especialidade.CARDIOLOGIA;
                        case 3 -> especialidade = Especialidade.GERIATRIA;
                        case 4 -> especialidade = Especialidade.ORTOPEDIA;
                        case 5 -> especialidade = Especialidade.CIRURGIA;
                        case 6 -> especialidade = Especialidade.MEDICINA_INTENSIVA;
                        default -> System.out.println("Especialidade inválida!");
                    }

                    if (especialidade != null) {
                        try {
                            hospital.cadastrarMedico(nomeM, crmM, especialidade);
                            System.out.println("Médico cadastrado com sucesso!");
                        } catch (Exception e) {
                            System.err.println("Erro ao cadastrar: " + e.getMessage());
                        }
                    }
                    break;
                }

                case 4: {
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Paciente: ");
                    String paciente = sc.nextLine();

                    System.out.print("Médico: ");
                    String medico = sc.nextLine();

                    System.out.println("Tipo de leito");
                    System.out.println("1 - Enfermaria");
                    System.out.println("2 - Apartamento");
                    System.out.println("3 - UTI");

                    System.out.println("Opcao: ");
                    int opcaoLeito = sc.nextInt();

                    TipoLeito tipoLeito = null;

                    switch (opcaoLeito) {
                        case 1 -> tipoLeito = TipoLeito.ENFERMARIA;
                        case 2 -> tipoLeito = TipoLeito.APARTAMENTO;
                        case 3 -> tipoLeito = TipoLeito.UTI;
                        default -> {
                            System.out.println("Opção inválida!");
                        }
                    }

                    System.out.print("Quarto: ");
                    String quarto = sc.nextLine();

                    System.out.print("Plano (enfermaria/apartamento/particular): ");
                    String plano = sc.nextLine();

                    hospital.registrarInternacao(
                            id, paciente, medico, tipoLeito, quarto,
                            java.time.LocalDate.now(), plano
                    );
                    break;
                }

                case 5: {
                    System.out.println("Funcionalidade de alta.");
                    break;
                }

                case 6: {
                    // Módulo de Pagamento (Jeff Queijos)
                    System.out.print("Digite o valor da internação: ");
                    double valor = sc.nextDouble();

                    System.out.println("Forma de pagamento:");
                    System.out.println("1 - PIX/Dinheiro (10% desconto)");
                    System.out.println("2 - Cartão (valor normal)");
                    System.out.println("3 - Parcelado (8% juros)");

                    int forma = sc.nextInt();
                    hospital.pagarInternacao(valor, forma);
                    break;
                }

                case 7: {
                    System.out.println("\n--- RELATÓRIOS ---");
                    hospital.relatorioPagamentos();
                    hospital.listarMedicos(); // Exibe seus médicos cadastrados
                    break;
                }

                case 0: {
                    System.out.println("Salvando dados no arquivo " + nomeArquivo + "...");
                    hospital.salvarHospital(nomeArquivo); // SALVAMENTO OBRIGATÓRIO
                    System.out.println("Sistema encerrado com sucesso.");
                    break;
                }

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
}