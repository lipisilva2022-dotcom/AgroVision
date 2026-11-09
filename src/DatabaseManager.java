import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class DatabaseManager {

    private final Connection conexao;

    // Construtor
    public DatabaseManager(Connection conexao) {
        this.conexao = conexao;
    }

    // Verifica se a conexão está ativa
    public boolean verificarConexao() throws SQLException {
        return conexao != null && conexao.isValid(2);
    }

    // Fecha a conexão
    public void fecharConexao() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }


    // ==========================================
    // RELATORIO MENSAL
    // ==========================================

    // Cadastrar relatório mensal
    public boolean cadastrarRelatorio(RelatorioMensal relatorio)
            throws SQLException {

        String sql = """
                INSERT INTO relatorio_mensal
                (data_referencia, faturamento, comissao,
                 margem_mensal, retirada_mensal, saldo)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(
                    relatorio.getDataReferencia()));

            stmt.setDouble(2, relatorio.getFaturamento());
            stmt.setDouble(3, relatorio.getComissao());
            stmt.setDouble(4, relatorio.getMargemMensal());
            stmt.setDouble(5, relatorio.getRetiradaMensal());
            stmt.setDouble(6, relatorio.getSaldo());

            return stmt.executeUpdate() > 0;
        }
    }

    // Consultar todos os relatórios
    public List<RelatorioMensal> listarRelatorios()
            throws SQLException {

        List<RelatorioMensal> relatorios = new ArrayList<>();

        String sql = "SELECT * FROM relatorio_mensal ORDER BY data_referencia";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {

                LocalDate data = resultado.getDate(
                        "data_referencia").toLocalDate();

                RelatorioMensal relatorio = new RelatorioMensal(
                        resultado.getInt("id"),
                        data,
                        resultado.getDouble("faturamento"),
                        resultado.getDouble("comissao"),
                        resultado.getDouble("margem_mensal"),
                        resultado.getDouble("retirada_mensal"),
                        resultado.getDouble("saldo")
                );

                relatorios.add(relatorio);
            }
        }

        return relatorios;
    }

    // Atualizar relatório mensal
    public boolean atualizarRelatorio(RelatorioMensal relatorio)
            throws SQLException {

        String sql = """
                UPDATE relatorio_mensal
                SET data_referencia = ?,
                    faturamento = ?,
                    comissao = ?,
                    margem_mensal = ?,
                    retirada_mensal = ?,
                    saldo = ?
                WHERE id = ?
                """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(
                    relatorio.getDataReferencia()));

            stmt.setDouble(2, relatorio.getFaturamento());
            stmt.setDouble(3, relatorio.getComissao());
            stmt.setDouble(4, relatorio.getMargemMensal());
            stmt.setDouble(5, relatorio.getRetiradaMensal());
            stmt.setDouble(6, relatorio.getSaldo());
            stmt.setInt(7, relatorio.getId());

            return stmt.executeUpdate() > 0;
        }
    }

    // Excluir relatório mensal
    public boolean excluirRelatorio(int id) throws SQLException {

        String sql = "DELETE FROM relatorio_mensal WHERE id = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }


    // ==========================================
    // METAS MENSAIS
    // ==========================================

    // Cadastrar meta mensal
    public boolean cadastrarMeta(MetaMensal meta)
            throws SQLException {

        String sql = """
                INSERT INTO metas_mensais
                (relatorio_id, meta, percentual_realizado)
                VALUES (?, ?, ?)
                """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, meta.getRelatorioId());
            stmt.setDouble(2, meta.getMeta());
            stmt.setDouble(3, meta.getPercentualRealizado());

            return stmt.executeUpdate() > 0;
        }
    }

    // Consultar todas as metas
    public List<MetaMensal> listarMetas() throws SQLException {

        List<MetaMensal> metas = new ArrayList<>();

        String sql = "SELECT * FROM metas_mensais ORDER BY relatorio_id";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {

                MetaMensal meta = new MetaMensal(
                        resultado.getInt("id"),
                        resultado.getInt("relatorio_id"),
                        resultado.getDouble("meta"),
                        resultado.getDouble("percentual_realizado")
                );

                metas.add(meta);
            }
        }

        return metas;
    }

    // Atualizar meta mensal
    public boolean atualizarMeta(MetaMensal meta)
            throws SQLException {

        String sql = """
                UPDATE metas_mensais
                SET relatorio_id = ?,
                    meta = ?,
                    percentual_realizado = ?
                WHERE id = ?
                """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, meta.getRelatorioId());
            stmt.setDouble(2, meta.getMeta());
            stmt.setDouble(3, meta.getPercentualRealizado());
            stmt.setInt(4, meta.getId());

            return stmt.executeUpdate() > 0;
        }
    }

    // Excluir meta mensal
    public boolean excluirMeta(int id) throws SQLException {

        String sql = "DELETE FROM metas_mensais WHERE id = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }


    // ==========================================
    // VENDAS DIRETAS
    // ==========================================

    // Cadastrar venda direta
    public boolean cadastrarVenda(VendaDireta venda)
            throws SQLException {

        String sql = """
                INSERT INTO vendas_diretas
                (relatorio_id, valor)
                VALUES (?, ?)
                """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, venda.getRelatorioId());
            stmt.setDouble(2, venda.getValor());

            return stmt.executeUpdate() > 0;
        }
    }

    // Consultar todas as vendas diretas
    public List<VendaDireta> listarVendas() throws SQLException {

        List<VendaDireta> vendas = new ArrayList<>();

        String sql = "SELECT * FROM vendas_diretas ORDER BY relatorio_id";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {

                VendaDireta venda = new VendaDireta(
                        resultado.getInt("id"),
                        resultado.getInt("relatorio_id"),
                        resultado.getDouble("valor")
                );

                vendas.add(venda);
            }
        }

        return vendas;
    }

    // Atualizar venda direta
    public boolean atualizarVenda(VendaDireta venda)
            throws SQLException {

        String sql = """
                UPDATE vendas_diretas
                SET relatorio_id = ?,
                    valor = ?
                WHERE id = ?
                """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, venda.getRelatorioId());
            stmt.setDouble(2, venda.getValor());
            stmt.setInt(3, venda.getId());

            return stmt.executeUpdate() > 0;
        }
    }

    // Excluir venda direta
    public boolean excluirVenda(int id) throws SQLException {

        String sql = "DELETE FROM vendas_diretas WHERE id = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }
}