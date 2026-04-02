# MedControl | Sistema de Gestão Hospitalar

> Projeto final da disciplina de Linguagem de Programação (LP) - Semestre 2025.2.

O **MedControl** é uma solução de software desenvolvida inteiramente em Java, projetada para operar via Interface de Linha de Comando (CLI). O sistema gerencia o fluxo completo de uma unidade hospitalar, desde o cadastro de pacientes e corpo clínico até o controle rigoroso de internações e faturamento automatizado.

---

## Resumo das Funcionalidades

O sistema foi arquitetado para suportar operações complexas do dia a dia de um hospital, garantindo a integridade dos dados através de validações estritas.

### Gestão de Pacientes
- Cadastro completo com validação estrutural de CPF e Telefone (11 dígitos).
- Vinculação obrigatória a um Plano de Saúde (Particular, Cobertura Total ou Coparticipação).
- Atualização dinâmica de planos sem perda do histórico do paciente.

### Gestão de Corpo Clínico
- Operações de cadastro e busca de médicos.
- Validação de formato de CRM (ex: 123456/UF).
- Classificação por especialidades médicas rigorosamente tipadas.
- Inativação lógica de profissionais (preservando o histórico de internações associadas).

### Controle de Internações
- **Regras de Negócio Aplicadas:** Bloqueio automático de internações em leitos não autorizados pelo plano do paciente (ex: Plano de Enfermaria não acessa leito de Apartamento).
- Geração de identificadores únicos (IDs) sequenciais.
- Registro de datas de entrada e alta para cálculo preciso de permanência.

### Faturamento e Faturas
- Cálculo automatizado do valor final cruzando os dias de permanência, o valor da diária do leito e o percentual do plano de saúde.
- Módulo de pagamentos com aplicação de regras de negócio financeiras:
  - Pagamentos à vista (PIX/Dinheiro): 10% de desconto.
  - Pagamentos a prazo (Cartão Parcelado): 8% de acréscimo.
- Exportação automatizada de relatórios de fechamento em arquivo `.txt`.

---

## Tecnologias e Padrões Utilizados

O desenvolvimento do MedControl priorizou as melhores práticas de engenharia de software.

- **Linguagem Principal:** Java (JDK 17+)
- **Testes de Unidade:** JUnit 4
- **Persistência de Dados:** Java Object Serialization (I/O)
- **Padrão de Arquitetura:** Facade (Fachada)
- **Princípios Aplicados:** - Alta coesão e baixo acoplamento através da separação de Gerenciadores e Modelos.
  - Encapsulamento estrito de regras de negócio dentro das classes de domínio.
  - Uso de Enums para garantir a segurança de tipos (Type Safety).

---

## Estrutura do Sistema

A arquitetura foi dividida em pacotes lógicos para facilitar a manutenção e escalabilidade do código:

* `com.hospital.medcontrol.model`: Contém as classes de domínio (entidades como Paciente, Medico, Internacao).
* `com.hospital.medcontrol.enums`: Define os domínios fechados do sistema (Especialidade, TipoLeito, FormaPagamento).
* `com.hospital.medcontrol.gerenciadores`: Classes de serviço que concentram a lógica de armazenamento e processamento das listas de dados.
* `view`: Contém a classe `Hospital.java`, que atua como Fachada, centralizando as chamadas do menu e ocultando a complexidade do sistema.
* `arquivos`: Infraestrutura de persistência genérica (`Persistencia<T>`).
* `com.hospital.medcontrol.main`: Ponto de entrada da aplicação, contendo o menu de interação com o usuário.

---

## Documentação e Testes

- **Diagrama de Classes:** A arquitetura completa do sistema encontra-se mapeada no diagrama UML fornecido na raiz do repositório.
- **Cobertura de Testes:** Foram desenvolvidos testes unitários automatizados para garantir a precisão matemática dos cálculos de faturamento e aplicação de regras de coparticipação de planos de saúde.

---

## Equipe de Desenvolvimento

- **Pedro Henrique Trajano da Silva** - Arquitetura e Desenvolvimento
- **Jeferson Souza de Paulo** - Desenvolvimento
- **José Everton Nascimento da Silva** - Desenvolvimento
- **Mariano Santos da Silva** - Desenvolvimento
