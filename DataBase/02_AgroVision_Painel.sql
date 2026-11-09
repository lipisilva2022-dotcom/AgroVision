-- =====================================================
-- AGROVISION - PAINEL DE CONTROLE
-- Arquivo: 02_AgroVision_Painel.sql
--
-- Finalidade:
-- Reunir consultas SQL para análise dos indicadores
-- financeiros e comerciais da empresa.
--
-- Este arquivo não cria tabelas.
-- Ele utiliza os dados armazenados no banco AgroVision.
-- =====================================================

USE AgroVision;

-- =====================================================
-- PAINEL DE CONTROLE - AGROVISION
-- Consultas para monitoramento e análise dos dados.
--
-- Este arquivo contém apenas comandos SELECT.
-- Nenhum registro será alterado ou excluído.
-- =====================================================


-- =====================================================
-- 1. VISUALIZAÇÃO GERAL DAS TABELAS
-- =====================================================

-- Exibe todos os relatórios mensais.
SELECT *
FROM relatorio_mensal
ORDER BY data_referencia;


-- Exibe todas as metas cadastradas.
SELECT *
FROM metas_mensais
ORDER BY relatorio_id;


-- Exibe todas as vendas diretas cadastradas.
SELECT *
FROM vendas_diretas
ORDER BY relatorio_id;


-- =====================================================
-- 2. RELATÓRIO MENSAL COMPLETO
-- =====================================================

-- Reúne as informações das três tabelas.
-- Permite visualizar os indicadores de cada mês.

SELECT
    r.id,
    r.data_referencia,
    r.faturamento,
    r.comissao,
    r.margem_mensal,
    r.retirada_mensal,
    r.saldo,
    m.meta,
    m.percentual_realizado,
    v.valor AS vendas_diretas

FROM relatorio_mensal r

LEFT JOIN metas_mensais m
    ON r.id = m.relatorio_id

LEFT JOIN vendas_diretas v
    ON r.id = v.relatorio_id

ORDER BY r.data_referencia;


-- =====================================================
-- 3. INDICADORES GERAIS
-- =====================================================

-- Apresenta os principais indicadores financeiros
-- registrados no banco de dados.

SELECT
    COUNT(*) AS total_meses,
    SUM(faturamento) AS faturamento_total,
    SUM(comissao) AS comissao_total,
    SUM(retirada_mensal) AS retiradas_totais,
    SUM(saldo) AS saldo_total

FROM relatorio_mensal;


-- =====================================================
-- 4. ACOMPANHAMENTO DAS METAS
-- =====================================================

-- Compara o faturamento com a meta estabelecida.
-- O percentual_realizado é apresentado conforme
-- o valor armazenado na tabela metas_mensais.

SELECT
    r.data_referencia,
    r.faturamento,
    m.meta,
    m.percentual_realizado

FROM relatorio_mensal r

INNER JOIN metas_mensais m
    ON r.id = m.relatorio_id

ORDER BY r.data_referencia;


-- =====================================================
-- 5. VENDAS DIRETAS
-- =====================================================

-- Apresenta os meses que possuem vendas diretas.

SELECT
    r.data_referencia,
    v.valor AS vendas_diretas

FROM vendas_diretas v

INNER JOIN relatorio_mensal r
    ON v.relatorio_id = r.id

ORDER BY r.data_referencia;


-- =====================================================
-- 6. MESES COM SALDO NEGATIVO
-- =====================================================

-- Identifica os meses em que as retiradas
-- ultrapassaram o valor das comissões.

SELECT
    data_referencia,
    comissao,
    retirada_mensal,
    saldo

FROM relatorio_mensal

WHERE saldo < 0

ORDER BY data_referencia;


-- =====================================================
-- 7. MESES SEM DADOS FINANCEIROS
-- =====================================================

-- Identifica os meses em que ainda não existem
-- informações de faturamento e comissão.

SELECT
    data_referencia,
    faturamento,
    comissao,
    saldo

FROM relatorio_mensal

WHERE faturamento IS NULL
   OR comissao IS NULL

ORDER BY data_referencia;


-- =====================================================
-- 8. RESUMO DAS METAS
-- =====================================================

-- Apresenta a quantidade de metas cadastradas,
-- o valor total estabelecido e o percentual médio
-- de realização das metas.

SELECT
    COUNT(*) AS total_metas,
    SUM(meta) AS valor_total_metas,
    AVG(percentual_realizado) AS percentual_medio_realizado

FROM metas_mensais;


-- =====================================================
-- 9. VERIFICAÇÃO DE INTEGRIDADE
-- =====================================================

-- Verifica se existem relatórios sem metas cadastradas.

SELECT
    r.id,
    r.data_referencia

FROM relatorio_mensal r

LEFT JOIN metas_mensais m
    ON r.id = m.relatorio_id

WHERE m.id IS NULL;


-- Verifica se existem relatórios sem vendas diretas.
-- Isso não significa necessariamente um erro,
-- pois nem todos os meses possuem vendas diretas.

SELECT
    r.id,
    r.data_referencia

FROM relatorio_mensal r

LEFT JOIN vendas_diretas v
    ON r.id = v.relatorio_id

WHERE v.id IS NULL;


-- =====================================================
-- FIM DO PAINEL DE CONTROLE - AGROVISION
-- =====================================================