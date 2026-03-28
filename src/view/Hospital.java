package view;

import arquivos.Persistencia;
import com.hospital.medcontrol.gerenciadores.GerenciadorMedicos;
import com.hospital.medcontrol.gerenciadores.GerenciadorPagamentos;
import com.hospital.medcontrol.model.Medico;
import com.hospital.medcontrol.enums.Especialidade;
import java.io.Serializable;

public class Hospital implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Persistencia<Hospital> persistencia = new Persistencia<>();

    // 1. ADICIONEM SEUS GERENCIADORES AQUI
    private GerenciadorMedicos gerMedicos = new GerenciadorMedicos();
    private GerenciadorPagamentos gerPagamentos = new GerenciadorPagamentos();

    // 2. COLOQUEM OS MÉTODOS QUE O MAIN VAI CHAMAR
    public void cadastrarMedico(String nome, String crm, Especialidade esp) {
        Medico novo = new Medico(nome, crm, esp);
        gerMedicos.cadastrarMedico(novo);
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