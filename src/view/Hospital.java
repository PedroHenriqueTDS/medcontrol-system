package view;

import arquivos.Persistencia;
import com.hospital.medcontrol.enums.TipoLeito;
import com.hospital.medcontrol.gerenciadores.GerenciadorDePaciente;
import com.hospital.medcontrol.gerenciadores.GerenciadorInternacaes;
import com.hospital.medcontrol.gerenciadores.GerenciadorMedicos;
import com.hospital.medcontrol.gerenciadores.GerenciadorPagamentos;
import com.hospital.medcontrol.model.Internacao;
import com.hospital.medcontrol.model.Medico;
import com.hospital.medcontrol.enums.Especialidade;
import com.hospital.medcontrol.model.Paciente;
import com.hospital.medcontrol.model.PlanoDeSaude;

import java.io.Serializable;
import java.time.LocalDate;

public class Hospital implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Persistencia<Hospital> persistencia = new Persistencia<>();

    // 1. ADICIONEM SEUS GERENCIADORES AQUI
    private GerenciadorDePaciente gerenciadorPaciente = new GerenciadorDePaciente();
    private GerenciadorMedicos gerMedicos = new GerenciadorMedicos();
    private GerenciadorPagamentos gerPagamentos = new GerenciadorPagamentos();
    private GerenciadorInternacaes gerInternacoes = new GerenciadorInternacaes();

    // 2. COLOQUEM OS MÉTODOS QUE O MAIN VAI CHAMAR
    public void cadastrarMedico(String nome, String crm, Especialidade esp) {
        Medico novo = new Medico(nome, crm, esp);
        gerMedicos.cadastrarMedico(novo);
    }

    public void registrarInternacao(int id, String paciente, String medico , TipoLeito tipoLeito, String quarto , LocalDate dataEntrada , String plano) {
        Internacao novaInternacao = new Internacao(id, paciente, medico, tipoLeito, quarto, dataEntrada, plano);
        gerInternacoes.registrarInternacao(novaInternacao);
    }

    public void cadastrarPaciente(String nome, String cpf, String telefone, PlanoDeSaude plano) {
        Paciente novo = new Paciente(nome, cpf, telefone, plano);
        gerenciadorPaciente.cadastrarPaciente(nome, cpf, telefone, plano);
    }
    public Paciente localizarPaciente(String cpf) {
        return gerenciadorPaciente.localizarPaciente(cpf);
    }

    public void listarMedicos() {
        gerMedicos.imprimirTodos();
    }

    // MÉTODOS DE PERSISTÊNCIA (Não alterem nada aqui embaixo pelas caridades)
    public void salvarHospital(String nomeArquivo) {
        persistencia.salvarEmArquivo(this, nomeArquivo);
    }

    public static Hospital carregarHospital(String nomeArquivo) {
        return persistencia.carregarDeArquivo(nomeArquivo);
    }
}