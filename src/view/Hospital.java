package view;

import com.hospital.medcontrol.enums.*;
import com.hospital.medcontrol.gerenciadores.*;
import com.hospital.medcontrol.model.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import arquivos.Persistencia;

public class Hospital implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Persistencia<Hospital> persistencia = new Persistencia<>();

    private GerenciadorDePaciente gerPacientes = new GerenciadorDePaciente();
    private GerenciadorMedicos gerMedicos = new GerenciadorMedicos();
    private GerenciadorInternacaes gerInternacoes = new GerenciadorInternacaes();
    private GerenciadorPagamentos gerPagamentos = new GerenciadorPagamentos();

    public void registrarInternacao(String cpf, String crm, TipoLeito leitoDesejado, String quarto, LocalDate entrada) {
        Paciente p = gerPacientes.localizarPaciente(cpf);
        if (p == null) throw new RuntimeException("Paciente não encontrado!");

        TipoLeito limite = p.getPlanoDeSaude().getTipoInternacaoPermitida();
        if (leitoDesejado == TipoLeito.APARTAMENTO && limite == TipoLeito.ENFERMARIA) {
            throw new RuntimeException("O plano de saúde não permite leito tipo Apartamento!");
        }

        int id = gerInternacoes.gerarNovoId();
        double perc = p.getPlanoDeSaude().getPercentualPagamento();

        Internacao nova = new Internacao(id, cpf, crm, leitoDesejado, quarto, entrada, perc);
        gerInternacoes.registrarInternacao(nova);
        System.out.println("Sucesso! Guarde o ID da internação: " + id);
    }

    public void darAlta(int id, LocalDate data) { gerInternacoes.registarAlta(id, data); }

    public void pagarInternacao(int id, int opcaoForma) {
        Internacao i = gerInternacoes.buscarPorId(id);
        if (i == null || i.isAtiva()) {
            System.out.println("Erro: Apenas internações com alta podem ser pagas.");
            return;
        }
        FormaPagamento forma = (opcaoForma == 1) ? FormaPagamento.PIX_DINHEIRO : (opcaoForma == 2) ? FormaPagamento.CARTAO : FormaPagamento.PARCELADO;
        gerPagamentos.registrarPagamento(i, forma);
        System.out.println("Pagamento registrado no sistema.");
    }

    public void cadastrarPaciente(String n, String c, String t, PlanoDeSaude p) { gerPacientes.cadastrarPaciente(n, c, t, p); }
    public Paciente localizarPaciente(String cpf) { return gerPacientes.localizarPaciente(cpf); }
    public void atualizarPlanoPaciente(String cpf, PlanoDeSaude plano) { gerPacientes.atualizarPlanoSaude(cpf, plano); }

    public void cadastrarMedico(String n, String c, Especialidade e) { gerMedicos.cadastrarMedico(new Medico(n, c, e)); }
    public void inativarMedico(String crm) { gerMedicos.removerMedico(crm); }
    public void listarMedicos() { gerMedicos.imprimirTodos(); }

    public List<Internacao> buscarInternacoesCpf(String cpf) { return gerInternacoes.buscarPorCpf(cpf); }
    public void listarInternacoesAtivas() { gerInternacoes.listarAtivas(); }
    public void listarInternacoesConcluidas() { gerInternacoes.listarConcluidas(); }

    public void relatorioPagamentos() { gerPagamentos.listarPagamentos(); }
    public void salvarHospital(String arq) { persistencia.salvarEmArquivo(this, arq); }
    public static Hospital carregarHospital(String arq) { return persistencia.carregarDeArquivo(arq); }
}