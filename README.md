# 🏋️ GymFlow: Aplicação ERP de Gerenciamento de Academias

O **GymFlow** é um sistema desktop desenvolvido em **Java** para o gerenciamento de alunos e instrutores de academia, focando no controle eficiente de cadastros, avaliações físicas e treinos personalizados, com ênfase na segurança da informação.

Este projeto foi elaborado como parte da disciplina de Programação Orientada a Objetos 2 do curso de Engenharia de Software da Universidade Tecnológica Federal do Paraná (UTFPR), Campus Cornélio Procópio.

---

## 🌟 Funcionalidades Principais

O sistema é segmentado por perfis de usuário, garantindo um controle de acesso eficiente.

### **1. Aluno**
* **Login Seguro:** O sistema permite que o Aluno faça login com usuário e senha válidos.
* **Visualização de Ficha de Treino:** Permite ver a Ficha de Treino atual, contendo exercícios, peso, repetições e séries.
* **Histórico:** Permite visualizar o histórico de Fichas de Treino anteriores e os resultados de Avaliações Físicas antigas (data, coeficiente de força, gordura corporal e peso).
* **Informações Pessoais:** Permite que o Aluno visualize o nome de seu Instrutor responsável e seu Plano de Treino.

### **2. Instrutor**
* **Login e Gestão de Alunos:** Permite login com usuário e senha e disponibiliza a ação "Ver Alunos" para listagem dos alunos do instrutor.
* **Montagem de Treino:**
    * Disponibiliza a ação "Montar treino para aluno".
    * Exibe o Treino Atual do aluno (quando existir).
    * Permite montar ou editar treino manualmente com até 4 exercícios.
    * Disponibiliza ação "Montar com IA" que preenche campos automaticamente (instrutor pode revisar/editar).
    * Permite SALVAR o treino montado/alterado.

### **3. Administrador**
* **Login e Logout:** Permite fazer login e logout com usuário e senha válidos.
* **CRUD de Alunos e Instrutores:**
    * Permite consultar, adicionar, alterar e excluir alunos.
    * Permite consultar, adicionar, alterar e excluir instrutores.

---

## ⚙️ Tecnologias e Arquitetura

O projeto foi desenvolvido seguindo as melhores práticas de Engenharia de Software.

| Componente | Tecnologia / Conceito | Detalhes |
| :--- | :--- | :--- |
| **Linguagem** | Java | Utilizando Programação Orientada a Objetos (POO). |
| **Interface Gráfica** | Java Swing | Ambiente de desenvolvimento Apache NetBeans e construtor visual Swing GUI Builder. |
| **Banco de Dados** | PostgreSQL | SGBD utilizado para gerenciamento e armazenamento de dados. |
| **Conexão BD** | JDBC API | Usado para realizar a conexão com o PostgreSQL. |
| **Arquitetura** | **MVC** (Model-View-Controller) | Arquitetura empregada para facilitar manutenção e expansão. |
| **Segurança** | **SHA-256** | Algoritmo de hashing aplicado às senhas para garantir a segurança. |
| **Boas Práticas** | **SOLID** | Aplicação do **Princípio da Responsabilidade Única** e **Princípio Aberto/Fechado**. |

---

## 🗃️ Estrutura e Boas Práticas

### **Padrão de Projeto MVC**
O projeto foi organizado em pastas para separar as responsabilidades:
* `Model`: Contém as classes entidades.
* `View`: Contém todos os *Forms* (telas) que aparecem para o usuário.
* `Control`: Realiza o controle com IA, banco de dados, autenticação, etc.

### **Princípio da Responsabilidade Única (SOLID)**
O CRUD (Criação, Consulta, Edição e Exclusão) de Alunos e Instrutores foi refatorado, onde cada função se tornou uma classe independente, aumentando a modularidade:
* `ApagarAluno`, `ConsultarAluno`, `CriarAluno`, `EditarAluno`.
* `Apagarinstrutor`, `Consultarinstrutor`, `Criarinstrutor`, `Editarinstrutor`.

### **Estrutura do Banco de Dados (Tabelas)**
O banco de dados PostgreSQL é composto pelas seguintes tabelas principais:

| Tabela | Colunas Chave (Exemplos) |
| :--- | :--- |
| `admin` | `id`, `cpf`, `nome`, `usuario`, `senha` |
| `aluno` | `id_aluno`, `cpf`, `nome`, `usuario`, `senha`, `instrutor_id`, `treino_id` |
| `instrutor` | `id_instrutor`, `cpf`, `nome`, `usuario`, `senha` |
| `avaliacao` | `id_avaliacao`, `aluno_id`, `coef_forca`, `peso`, `bf`, `data_avaliacao` |
| `exercicio` | `id_exercicio`, `treino_id`, `nome`, `peso`, `repeticoes` |

---

## 🚀 Como Executar o Projeto

**Pré-requisitos:**

1.  **Java Development Kit (JDK):** Versão compatível com o projeto.
2.  **Apache NetBeans:** Ambiente de desenvolvimento (IDE) recomendado.
3.  **PostgreSQL:** Servidor de banco de dados instalado e configurado.
4.  **Driver JDBC para PostgreSQL:** Necessário para a conexão com o banco de dados.

---

## 🤝 Contribuição

Este projeto foi desenvolvido por:

* **Autor:** Rafael Trevizan
* **Disciplina:** Programação Orientada a Objetos 2
* **Instituição:** Universidade Tecnológica Federal do Paraná, Campus Cornélio Procópio
* **Professora:** Gisele Alves Santana
