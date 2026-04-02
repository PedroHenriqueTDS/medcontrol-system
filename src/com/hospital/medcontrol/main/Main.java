package com.hospital.medcontrol.main;

import com.hospital.medcontrol.enums.*;
import com.hospital.medcontrol.model.*;
import view.Hospital;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String arq = "hospital.ser";

        System.out.println("Iniciando sistema e carregando banco de dados...");
        Hospital hospital = Hospital.carregarHospital(arq);
        if (hospital == null) {
            System.out.println("[AVISO] Primeiro acesso detectado. Criando novo banco de dados.");
            hospital = new Hospital();
        }

        int opcao = -1;

        do {
            exibirMenuPrincipal();
            opcao = lerInteiroComIntervalo(sc, "Digite a opçao desejada: ", 0, 7);

            switch (opcao) {
                case 1 -> menuCadastrarPaciente(hospital, sc);
                case 2 -> menuAtualizarPlano(hospital, sc);
                case 3 -> menuGerenciarMedicos(hospital, sc);
                case 4 -> menuRegistrarInternacao(hospital, sc);
                case 5 -> menuRegistrarAlta(hospital, sc);
                case 6 -> menuPagarFatura(hospital, sc);
                case 7 -> menuRelatorios(hospital, sc);
                case 0 -> encerrarSistema(hospital, arq);
            }
        } while (opcao != 0);

        sc.close();
    }



    private static void menuCadastrarPaciente(Hospital hospital, Scanner sc) {
        imprimirCabecalho("CADASTRO DE PACIENTE");

        String nome = lerString(sc, "Nome completo: ");
        String cpf = lerString(sc, "CPF (Apenas os 11 numeros): ");
        String telefone = lerString(sc, "Telefone (Apenas os 11 numeros): ");
        String operadora = lerString(sc, "Operadora do Plano (ou 'Particular'): ");

        System.out.println("\n[ TIPO DE LEITO PERMITIDO ]");
        System.out.println("1 - Enfermaria");
        System.out.println("2 - Apartamento");
        int opcaoLeito = lerInteiroComIntervalo(sc, "Selecione o leito (1 ou 2): ", 1, 2);
        TipoLeito leito = (opcaoLeito == 1) ? TipoLeito.ENFERMARIA : TipoLeito.APARTAMENTO;

        System.out.println("\n[ COPARTICIPACAO ]");
        System.out.println("1 - 0% (Cobertura Total)");
        System.out.println("2 - 15% (Coparticipacao Padrao)");
        System.out.println("3 - 100% (Particular)");
        int opcaoPerc = lerInteiroComIntervalo(sc, "Selecione a coparticipacao (1 a 3): ", 1, 3);
        double percentual = (opcaoPerc == 1) ? 0.0 : (opcaoPerc == 2) ? 0.15 : 1.0;

        try {
            PlanoDeSaude plano = new PlanoDeSaude(operadora, leito, percentual);
            hospital.cadastrarPaciente(nome, cpf, telefone, plano);
            System.out.println("\n[SUCESSO] Paciente cadastrado no sistema!");
        } catch (Exception e) {
            System.out.println("\n[ERRO] Falha ao cadastrar: " + e.getMessage());
        }
        esperarEnter(sc);
    }

    private static void menuAtualizarPlano(Hospital hospital, Scanner sc) {
        imprimirCabecalho("ATUALIZAR PLANO DE PACIENTE");

        String cpf = lerString(sc, "CPF do Paciente: ");
        Paciente paciente = hospital.localizarPaciente(cpf);

        if (paciente == null) {
            System.out.println("\n[AVISO] Paciente nao encontrado.");
        } else {
            System.out.println("Paciente localizado: " + paciente.getNome());
            String operadora = lerString(sc, "Nova Operadora: ");

            int opcaoLeito = lerInteiroComIntervalo(sc, "Novo Leito (1-Enfermaria, 2-Apartamento): ", 1, 2);
            TipoLeito leito = (opcaoLeito == 1) ? TipoLeito.ENFERMARIA : TipoLeito.APARTAMENTO;

            int opcaoPerc = lerInteiroComIntervalo(sc, "Nova Coparticipacao (1-0%, 2-15%, 3-100%): ", 1, 3);
            double percentual = (opcaoPerc == 1) ? 0.0 : (opcaoPerc == 2) ? 0.15 : 1.0;

            try {
                hospital.atualizarPlanoPaciente(cpf, new PlanoDeSaude(operadora, leito, percentual));
                System.out.println("\n[SUCESSO] Plano atualizado com sucesso!");
            } catch (Exception e) {
                System.out.println("\n[ERRO] " + e.getMessage());
            }
        }
        esperarEnter(sc);
    }

    private static void menuGerenciarMedicos(Hospital hospital, Scanner sc) {
        imprimirCabecalho("GERENCIAR MEDICOS");
        System.out.println("1 - Cadastrar novo Medico");
        System.out.println("2 - Remover Medico (Inativacao logica)");
        int sub = lerInteiroComIntervalo(sc, "Selecione a acao: ", 1, 2);

        if (sub == 1) {
            String nome = lerString(sc, "Nome do Medico: ");
            String crm = lerString(sc, "CRM (Formato 123456/UF): ");

            System.out.println("\n[ ESPECIALIDADE ]");
            System.out.println("1-Clinica Geral | 2-Cardiologia | 3-Geriatria");
            System.out.println("4-Ortopedia     | 5-Cirurgia    | 6-Intensiva");
            int espOp = lerInteiroComIntervalo(sc, "Selecione a especialidade (1 a 6): ", 1, 6);

            Especialidade esp = switch (espOp) {
                case 1 -> Especialidade.CLINICA_MEDICA_GERAL;
                case 2 -> Especialidade.CARDIOLOGIA;
                case 3 -> Especialidade.GERIATRIA;
                case 4 -> Especialidade.ORTOPEDIA;
                case 5 -> Especialidade.CIRURGIA;
                default -> Especialidade.MEDICINA_INTENSIVA;
            };

            try {
                hospital.cadastrarMedico(nome, crm, esp);
                System.out.println("\n[SUCESSO] Medico cadastrado no sistema!");
            } catch (Exception e) {
                System.out.println("\n[ERRO] " + e.getMessage());
            }
        } else {
            String crm = lerString(sc, "CRM do Medico a ser removido: ");
            try {
                hospital.inativarMedico(crm);
                System.out.println("\n[SUCESSO] Medico inativado com sucesso.");
            } catch (Exception e) {
                System.out.println("\n[ERRO] " + e.getMessage());
            }
        }
        esperarEnter(sc);
    }

    private static void menuRegistrarInternacao(Hospital hospital, Scanner sc) {
        imprimirCabecalho("REGISTRO DE INTERNACAO");
        String cpf = lerString(sc, "CPF do Paciente: ");
        String crm = lerString(sc, "CRM do Medico Responsavel: ");

        System.out.println("\n[ TIPO DE LEITO SOLICITADO ]");
        System.out.println("1 - Enfermaria");
        System.out.println("2 - Apartamento");
        System.out.println("3 - UTI");
        int opcaoLeito = lerInteiroComIntervalo(sc, "Selecione o leito (1 a 3): ", 1, 3);
        TipoLeito leito = (opcaoLeito == 1) ? TipoLeito.ENFERMARIA : (opcaoLeito == 2) ? TipoLeito.APARTAMENTO : TipoLeito.UTI;

        String quarto = lerString(sc, "Identificacao do Quarto/Ala: ");

        System.out.println("\n[ DATA DE ENTRADA ]");
        int dia = lerInteiroComIntervalo(sc, "Dia (1-31): ", 1, 31);
        int mes = lerInteiroComIntervalo(sc, "Mes (1-12): ", 1, 12);
        int ano = lerInteiro(sc, "Ano (ex: 2025): ");

        try {
            LocalDate dataEntrada = LocalDate.of(ano, mes, dia);
            hospital.registrarInternacao(cpf, crm, leito, quarto, dataEntrada);
        } catch (Exception e) {
            System.out.println("\n[ERRO] Falha na Internacao: " + e.getMessage());
        }
        esperarEnter(sc);
    }

    private static void menuRegistrarAlta(Hospital hospital, Scanner sc) {
        imprimirCabecalho("ALTA DE PACIENTE");
        int id = lerInteiro(sc, "Digite o ID unico da Internacao: ");

        System.out.println("\n[ DATA DA ALTA ]");
        int dia = lerInteiroComIntervalo(sc, "Dia (1-31): ", 1, 31);
        int mes = lerInteiroComIntervalo(sc, "Mes (1-12): ", 1, 12);
        int ano = lerInteiro(sc, "Ano (ex: 2025): ");

        try {
            LocalDate dataAlta = LocalDate.of(ano, mes, dia);
            hospital.darAlta(id, dataAlta);
        } catch (Exception e) {
            System.out.println("\n[ERRO] Falha ao registrar alta. Verifique a validade da data informada.");
        }
        esperarEnter(sc);
    }

    private static void menuPagarFatura(Hospital hospital, Scanner sc) {
        imprimirCabecalho("PAGAMENTO DE FATURA");
        int id = lerInteiro(sc, "ID da Internacao Concluida: ");

        System.out.println("\n[ FORMA DE PAGAMENTO ]");
        System.out.println("1 - PIX ou Dinheiro (10% de desconto)");
        System.out.println("2 - Cartao de Credito a Vista");
        System.out.println("3 - Parcelado em 3x (Acrescimo de 8% de juros)");
        int forma = lerInteiroComIntervalo(sc, "Selecione a forma (1 a 3): ", 1, 3);

        System.out.println("");
        hospital.pagarInternacao(id, forma);
        esperarEnter(sc);
    }

    private static void menuRelatorios(Hospital hospital, Scanner sc) {
        imprimirCabecalho("RELATORIOS E BUSCAS");
        System.out.println("1 - Buscar Internacoes pelo CPF do Paciente");
        System.out.println("2 - Listar Todas as Internacoes Ativas");
        System.out.println("3 - Listar Todas as Internacoes Concluidas");
        System.out.println("4 - Relatorio de Pagamentos (Gera arquivo TXT)");
        System.out.println("5 - Listar Medicos Ativos no Sistema");
        int opcao = lerInteiroComIntervalo(sc, "Selecione o relatorio (1 a 5): ", 1, 5);

        System.out.println("\n--------------------------------------------------");
        switch (opcao) {
            case 1 -> {
                String cpf = lerString(sc, "Digite o CPF: ");
                List<Internacao> ints = hospital.buscarInternacoesCpf(cpf);
                if (ints.isEmpty()) {
                    System.out.println("Nenhuma internacao encontrada para este CPF.");
                } else {
                    for (Internacao i : ints) System.out.println(i);
                }
            }
            case 2 -> hospital.listarInternacoesAtivas();
            case 3 -> hospital.listarInternacoesConcluidas();
            case 4 -> hospital.relatorioPagamentos();
            case 5 -> hospital.listarMedicos();
        }
        System.out.println("--------------------------------------------------");
        esperarEnter(sc);
    }

    private static void encerrarSistema(Hospital hospital, String arq) {
        System.out.println("\nSalvando dados do hospital no disco...");
        hospital.salvarHospital(arq);
        System.out.println("Dados salvos com sucesso.");
        System.out.println("Sessao encerrada. Ate logo!");
    }


    private static void exibirMenuPrincipal() {
        System.out.println("\n==================================================");
        System.out.println("          SISTEMA HOSPITALAR MEDCONTROL           ");
        System.out.println("==================================================");
        System.out.println("  1 - Cadastrar Paciente");
        System.out.println("  2 - Atualizar Plano do Paciente");
        System.out.println("  3 - Gerenciar Medicos");
        System.out.println("  4 - Registrar Internacao");
        System.out.println("  5 - Registrar Alta de Paciente");
        System.out.println("  6 - Realizar Pagamento");
        System.out.println("  7 - Relatorios e Consultas");
        System.out.println("  0 - Salvar Sistema e Sair");
        System.out.println("==================================================");
    }

    private static void imprimirCabecalho(String titulo) {
        System.out.println("\n==================================================");
        int espacos = (50 - titulo.length()) / 2;
        System.out.printf("%" + (espacos + titulo.length()) + "s%n", titulo);
        System.out.println("==================================================");
    }

    private static String lerString(Scanner sc, String mensagem) {
        String input;
        do {
            System.out.print(mensagem);
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("[ERRO] Este campo nao pode ser vazio. Tente novamente.");
            }
        } while (input.isEmpty());
        return input;
    }

    private static int lerInteiro(Scanner sc, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = sc.nextInt();
                sc.nextLine();
                return valor;
            } catch (java.util.InputMismatchException e) {
                System.out.println("[ERRO] Entrada invalida. Digite apenas numeros.");
                sc.nextLine();
            }
        }
    }

    private static int lerInteiroComIntervalo(Scanner sc, String mensagem, int min, int max) {
        while (true) {
            int valor = lerInteiro(sc, mensagem);
            if (valor >= min && valor <= max) {
                return valor;
            } else {
                System.out.println("[ERRO] Opcao invalida. Digite um numero entre " + min + " e " + max + ".");
            }
        }
    }

    private static void esperarEnter(Scanner sc) {
        System.out.println("\n[ Pressione ENTER para voltar ao Menu Principal ]");
        sc.nextLine();
    }
}
