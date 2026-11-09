-- =====================================================
-- BANCO DE DADOS: AgroVision
-- Projeto de gerenciamento e análise de dados comerciais.
-- =====================================================


create database AgroVision;

USE AgroVision;

-- =====================================================
-- TABELA: relatorio_mensal
-- Armazena os indicadores financeiros de cada mês.
--
-- id: identificador único do registro (chave primária).
-- data_referencia: mês e ano de referência do relatório.
-- faturamento: valor total faturado no período.
-- comissao: valor de comissão recebido.
-- margem_mensal: percentual de margem mensal.
-- retirada_mensal: valor retirado no período.
-- saldo: diferença entre comissão e retiradas.
--
-- A data_referencia é UNIQUE para impedir que o mesmo
-- mês seja cadastrado mais de uma vez.
-- =====================================================

CREATE TABLE relatorio_mensal (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_referencia DATE NOT NULL UNIQUE,
    faturamento DECIMAL(15,2),
    comissao DECIMAL(15,2),
    margem_mensal DECIMAL(8,2),
    retirada_mensal DECIMAL(15,2),
    saldo DECIMAL(15,2)
);

-- =====================================================
-- TABELA: metas_mensais
-- Armazena as metas comerciais de cada mês.
--
-- id: identificador único da meta.
-- relatorio_id: referência ao relatório mensal.
-- meta: valor financeiro estabelecido como objetivo.
-- percentual_realizado: percentual da meta atingido.
--
-- A chave estrangeira relatorio_id estabelece o vínculo
-- com a tabela relatorio_mensal.
-- =====================================================

CREATE TABLE metas_mensais (
    id INT AUTO_INCREMENT PRIMARY KEY,
    relatorio_id INT NOT NULL UNIQUE,
    meta DECIMAL(15,2),
    percentual_realizado DECIMAL(10,2),

    FOREIGN KEY (relatorio_id)
        REFERENCES relatorio_mensal(id)
);

-- =====================================================
-- TABELA: vendas_diretas
-- Armazena os valores das vendas realizadas diretamente.
--
-- id: identificador único do registro.
-- relatorio_id: referência ao relatório mensal.
-- valor: valor financeiro das vendas diretas.
--
-- A chave estrangeira relatorio_id estabelece o vínculo
-- com a tabela relatorio_mensal.
-- =====================================================

-- A estrutura deve seguir a definição já criada no banco.
-- Consulte DESCRIBE vendas_diretas antes de recriá-la.

CREATE TABLE vendas_diretas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    relatorio_id INT NOT NULL UNIQUE,
    valor DECIMAL(15,2),

    FOREIGN KEY (relatorio_id)
        REFERENCES relatorio_mensal(id)
);