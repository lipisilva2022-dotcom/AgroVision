# 🌱 AgroVision

Sistema de gerenciamento e análise de informações financeiras e comerciais, desenvolvido como Projeto Integrado do curso de Análise e Desenvolvimento de Sistemas.

O AgroVision tem como objetivo centralizar informações comerciais e financeiras, facilitando o acompanhamento de faturamento, comissões, margens, retiradas, saldos, metas mensais e vendas diretas.

## 🎯 Objetivo do projeto

Desenvolver uma solução que permita organizar os dados financeiros e comerciais em um banco de dados relacional, realizar operações por meio de uma aplicação Java e disponibilizar informações para análise em um painel desenvolvido no Power BI.

## 🛠️ Tecnologias utilizadas

- **Java:** desenvolvimento da aplicação e implementação das operações com os dados.
- **MySQL:** armazenamento e gerenciamento das informações.
- **JDBC:** comunicação entre a aplicação Java e o banco de dados.
- **Power BI:** criação do painel de indicadores e análise dos dados.
- **Git e GitHub:** versionamento e armazenamento do código-fonte.

## 🗂️ Estrutura do projeto

```text
AgroVision/
│
├── DataBase/
│   ├── 01_AgroVision_Banco.sql
│   └── 02_AgroVision_Painel.sql
│
├── src/
│   ├── AgroVision.java
│   ├── Conexao.java
│   ├── DatabaseManager.java
│   ├── RelatorioMensal.java
│   ├── MetaMensal.java
│   ├── VendaDireta.java
│   └── TesteConexao.java
│
└── README.md
```

## 🗄️ Banco de dados

O banco de dados AgroVision foi desenvolvido no MySQL e possui três tabelas principais:

### relatorio_mensal
Armazena os indicadores financeiros de cada mês:
- Data de referência;
- Faturamento;
- Comissão;
- Margem mensal;
- Retirada mensal;
- Saldo.

### metas_mensais
Armazena as metas comerciais:
- Relatório relacionado;
- Valor da meta;
- Percentual realizado.

### vendas_diretas
Armazena os valores das vendas realizadas diretamente, vinculados ao relatório mensal correspondente.

## 💻 Aplicação Java

A aplicação utiliza uma interface de menu no terminal para realizar operações relacionadas aos relatórios mensais.

Funcionalidades disponíveis no menu:
- Cadastro de relatórios;
- Consulta de relatórios por mês e ano;
- Listagem dos relatórios cadastrados;
- Atualização de relatórios.

A camada `DatabaseManager` concentra as operações de acesso ao banco de dados, enquanto as classes de modelo representam as informações armazenadas nas tabelas.

## 📊 Power BI

O projeto também utiliza o Power BI para apresentar indicadores financeiros e comerciais por meio de um painel de controle.

As consultas SQL disponíveis em `DataBase/02_AgroVision_Painel.sql` reúnem informações das tabelas e apoiam a análise dos dados.

## ⚙️ Como executar o projeto

1. Instale o Java JDK.
2. Instale o MySQL Server e o MySQL Workbench.
3. Execute o script `01_AgroVision_Banco.sql` para criar a estrutura do banco de dados.
4. Configure os dados de conexão no arquivo `Conexao.java`.
5. Adicione o driver JDBC do MySQL ao projeto.
6. Abra o projeto em uma IDE Java, como IntelliJ IDEA.
7. Execute a classe `AgroVision.java`.

## 📌 Status do projeto

Projeto Integrado em desenvolvimento, com estrutura do banco de dados, aplicação Java e painel de análise de dados.

## 👨‍💻 Desenvolvedor

Felipe Silva de Assis

Curso: Análise e Desenvolvimento de Sistemas — UNIFEOB.
