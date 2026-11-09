import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {

    private final Connection conexao;

    // Construtor: recebe a conexão com o banco.
    public DatabaseManager(Connection conexao) {
        this.conexao = conexao;
    }

    // Verifica se a conexão está ativa.
    public boolean verificarConexao() throws SQLException {
        return conexao != null && !conexao.isClosed();
    }

    // Encerra a conexão com o banco.
    public void fecharConexao() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }
}
